package org.codegas.security.service_impl.api;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collection;

import org.codegas.commons.lang.annotation.Transactional;
import org.codegas.security.domain.entity.Credential;
import org.codegas.security.domain.entity.User;
import org.codegas.security.domain.repository.CredentialRepository;
import org.codegas.security.domain.repository.UserRepository;
import org.codegas.security.domain.value.CredentialId;
import org.codegas.security.domain.value.UserAttribute;
import org.codegas.security.domain.value.UserId;
import org.codegas.security.service.api.Authorization;
import org.codegas.security.service.api.AuthorizationException;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.jackson2.JacksonFactory;

@Transactional
public class GoogleAuthService extends AbstractAuthService {

    // Available Scopes are documented at https://developers.google.com/identity/protocols/googlescopes
    private static final Collection<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/userinfo.email", "https://www.googleapis.com/auth/userinfo.profile");

    private final GoogleAuthorizationCodeFlow authorizationCodeFlow;

    public GoogleAuthService(CredentialRepository credentialRepository, UserRepository userRepository, GoogleAuthConfig config) {
        super(credentialRepository, userRepository);
        this.authorizationCodeFlow = new GoogleAuthorizationCodeFlow
            .Builder(config.getHttpTransport(), JacksonFactory.getDefaultInstance(), config.getClientId(), config.getClientSecret(), SCOPES)
            .setTokenServerUrl(new GenericUrl("https://www.googleapis.com/oauth2/v4/token"))
            .setAccessType("offline")
            .setApprovalPrompt("force")
            .build();
    }

    @Override
    public URI authorize(URI callbackUri, URI appStateUri) {
        String authorizationUri = authorizationCodeFlow.newAuthorizationUrl()
            .setRedirectUri(callbackUri.toString())
            .setState(appStateUri.toString())
            .build();
        return URI.create(authorizationUri);
    }

    @Override
    public Authorization<String> logIn(URI callbackUri, String authCode) throws AuthorizationException {
        try {
            GoogleTokenResponse tokenResponse = authorizationCodeFlow.newTokenRequest(authCode).setRedirectUri(callbackUri.toString()).execute();
            GoogleIdToken.Payload tokenPayload = tokenResponse.parseIdToken().getPayload();

            Credential credential = getCredential(tokenResponse, tokenPayload);
            User user = getUser(tokenPayload);
            user.refreshCredential(credential);

            return new Authorization<>("Bearer", credential.getAccessToken());
        } catch (Exception e) {
            throw new AuthorizationException(e);
        }
    }

    @Override
    public Authorization<String> reauthenticate(Authorization<String> authorization) throws AuthorizationException {
        // TODO Use Refresh Token if it exists in the Credential
        return credentialRepository.findByAccessToken(authorization.getToken())
            .filter(Credential::isFresh)
            .map(credential -> new Authorization<>("Bearer", credential.getAccessToken()))
            .orElseThrow(AuthorizationException::new);
    }

    private Credential getCredential(TokenResponse tokenResponse, GoogleIdToken.Payload tokenPayload) {
        CredentialId credentialId = CredentialId.oauth2("GOOGLE", tokenPayload.getSubject());
        return credentialRepository.find(credentialId)
            .map(credential -> refresh(credential, tokenResponse))
            .orElseGet(() -> credentialRepository.add(refresh(new Credential(credentialId), tokenResponse)));
    }

    private User getUser(GoogleIdToken.Payload tokenPayload) {
        UserId userId = UserId.email(tokenPayload.getEmail());
        User user = userRepository.find(userId).orElseGet(() -> userRepository.add(new User(userId)));
        user.setAttribute(UserAttribute.EMAIL, tokenPayload.getEmail());
        user.setAttribute(UserAttribute.AVATAR, String.class.cast(tokenPayload.get("picture")));
        user.setAttributeIfAbsent(UserAttribute.NAME, String.class.cast(tokenPayload.get("name")));
        return user;
    }

    private static Credential refresh(Credential credential, TokenResponse tokenResponse) {
        return credential.apply(tokenResponse.getAccessToken(), tokenResponse.getRefreshToken(), calculateExpiration(tokenResponse.getExpiresInSeconds()));
    }

    private static OffsetDateTime calculateExpiration(Long expiresInSeconds) {
        return expiresInSeconds == null ? OffsetDateTime.MAX : OffsetDateTime.now().plusSeconds(expiresInSeconds);
    }
}
