package org.codegas.security.service_impl.api_impl;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.core.SecurityContext;

import org.codegas.commons.lang.annotation.Transactional;
import org.codegas.security.domain.entity.Credential;
import org.codegas.security.domain.entity.User;
import org.codegas.security.domain.entity.UserCredential;
import org.codegas.security.domain.repository.CredentialRepository;
import org.codegas.security.domain.repository.UserRepository;
import org.codegas.security.domain.value.CredentialId;
import org.codegas.security.domain.value.UserAttributeType;
import org.codegas.security.domain.value.UserId;
import org.codegas.security.service.api.AuthorizationService;
import org.codegas.security.service.dto.AuthenticatedUserDto;
import org.codegas.security.service_impl.factory.AuthenticatedUserDtoFactory;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Named
@Singleton
@Transactional
public class GoogleAuthorizationService implements AuthorizationService {

    // Available Scopes are documented at https://developers.google.com/identity/protocols/googlescopes
    private static final Collection<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/userinfo.email", "https://www.googleapis.com/auth/userinfo.profile");

    private static final String BEARER = "Bearer";

    private final GoogleAuthorizationCodeFlow authorizationCodeFlow;

    private final CredentialRepository credentialRepository;

    private final UserRepository userRepository;

    @Inject
    public GoogleAuthorizationService(
        @Named("googleHttpTransport") HttpTransport httpTransport,
        @Named("googleClientId") String googleClientId,
        @Named("googleClientSecret") String googleClientSecret,
        CredentialRepository credentialRepository,
        UserRepository userRepository) {
        this.authorizationCodeFlow = new GoogleAuthorizationCodeFlow
            .Builder(httpTransport, JacksonFactory.getDefaultInstance(), googleClientId, googleClientSecret, SCOPES)
            .setTokenServerUrl(new GenericUrl("https://www.googleapis.com/oauth2/v4/token"))
            .build();
        this.credentialRepository = credentialRepository;
        this.userRepository = userRepository;
    }

    @Override
    public URI authorize(String redirectUri) {
        return URI.create(authorizationCodeFlow.newAuthorizationUrl().setRedirectUri(redirectUri).build());
    }

    @Override
    public AuthenticatedUserDto logIn(String redirectUri, String authCode) {
        try {
            GoogleTokenResponse tokenResponse = authorizationCodeFlow.newTokenRequest(authCode).setRedirectUri(redirectUri).execute();
            GoogleIdToken.Payload tokenPayload = tokenResponse.parseIdToken().getPayload();

            User user = getUser(UserId.email(tokenPayload.getEmail()), tokenPayload);
            Credential credential = getCredential(CredentialId.oauth2("GOOGLE", tokenPayload.getSubject()), tokenResponse);
            UserCredential userCredential = user.refreshCredential(credential);

            return AuthenticatedUserDtoFactory.createDto(userCredential);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to log User in", e);
        }
    }

    @Override
    public SecurityContext authenticate(String requestScheme, String authorization) {
        return extractBearerToken(authorization)
            .flatMap(credentialRepository::findByAccessToken)
            .filter(Credential::isFresh)
            .flatMap(userRepository::findByCredential)
            .<SecurityContext>map(user -> new AuthenticatedUserSecurityContext(requestScheme, BEARER, user))
            .orElseGet(UnauthenticatedSecurityContext::new);
    }

    private User getUser(UserId userId, GoogleIdToken.Payload tokenPayload) {
        User user = userRepository.find(userId).orElseGet(() -> userRepository.add(new User(userId)));
        user.setAttribute(UserAttributeType.EMAIL, tokenPayload.getEmail());
        user.setAttribute(UserAttributeType.AVATAR, String.class.cast(tokenPayload.get("picture")));
        return user;
    }

    private Credential getCredential(CredentialId credentialId, TokenResponse tokenResponse) {
        return credentialRepository.find(credentialId)
            .orElseGet(() -> credentialRepository.add(new Credential(credentialId)))
            .apply(tokenResponse.getAccessToken(), tokenResponse.getRefreshToken(), convertToExpiration(tokenResponse.getExpiresInSeconds()));
    }

    private static OffsetDateTime convertToExpiration(Long expiresInSeconds) {
        return expiresInSeconds == null ? OffsetDateTime.MAX : OffsetDateTime.now().plusSeconds(expiresInSeconds);
    }

    private static Optional<String> extractBearerToken(String authorization) {
        return authorization != null && authorization.startsWith(BEARER) ? Optional.of(authorization.substring(BEARER.length()).trim()) : Optional.empty();
    }
}
