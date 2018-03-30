package org.codegas.security.service_impl.api_impl;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.lang.annotation.Transactional;
import org.codegas.security.domain.entity.Credential;
import org.codegas.security.domain.entity.User;
import org.codegas.security.domain.entity.UserCredential;
import org.codegas.security.domain.repository.CredentialRepository;
import org.codegas.security.domain.repository.UserRepository;
import org.codegas.security.domain.value.CredentialId;
import org.codegas.security.domain.value.UserAttribute;
import org.codegas.security.domain.value.UserId;
import org.codegas.security.service.api.AuthorizationException;
import org.codegas.security.service.api.Authorization;
import org.codegas.security.service.api.AuthorizationService;
import org.codegas.security.service.dto.AuthenticatedUserDto;
import org.codegas.security.service.dto.UserDto;
import org.codegas.security.service_impl.factory.AuthenticatedUserDtoFactory;
import org.codegas.security.service_impl.factory.UserDtoFactory;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.jackson2.JacksonFactory;

@Named
@Singleton
@Transactional
public class GoogleAuthorizationService implements AuthorizationService {

    // Available Scopes are documented at https://developers.google.com/identity/protocols/googlescopes
    private static final Collection<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/userinfo.email", "https://www.googleapis.com/auth/userinfo.profile");

    private final GoogleAuthorizationCodeFlow authorizationCodeFlow;

    private final CredentialRepository credentialRepository;

    private final UserRepository userRepository;

    @Inject
    public GoogleAuthorizationService(GoogleAuthorizationConfig config, CredentialRepository credentialRepository, UserRepository userRepository) {
        this.authorizationCodeFlow = new GoogleAuthorizationCodeFlow
            .Builder(config.getHttpTransport(), JacksonFactory.getDefaultInstance(), config.getClientId(), config.getClientSecret(), SCOPES)
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
    public AuthenticatedUserDto logIn(String redirectUri, String authCode) throws AuthorizationException {
        try {
            GoogleTokenResponse tokenResponse = authorizationCodeFlow.newTokenRequest(authCode).setRedirectUri(redirectUri).execute();
            GoogleIdToken.Payload tokenPayload = tokenResponse.parseIdToken().getPayload();

            Credential credential = getCredential(tokenResponse, tokenPayload);
            User user = getUser(tokenPayload);
            UserCredential userCredential = user.refreshCredential(credential);

            return AuthenticatedUserDtoFactory.createDto(userCredential);
        } catch (Exception e) {
            throw new AuthorizationException(e);
        }
    }

    @Override
    public UserDto authenticate(Authorization<String> authorization) throws AuthorizationException {
        return credentialRepository.findByAccessToken(authorization.getToken())
            .filter(Credential::isFresh)
            .flatMap(userRepository::findByCredential)
            .map(UserDtoFactory::createDto)
            .orElseThrow(AuthorizationException::new);
    }

    @Override
    public UserDto logOut(Authorization<String> authorization) throws AuthorizationException {
        User user = credentialRepository.findByAccessToken(authorization.getToken())
            .filter(Credential::isFresh)
            .flatMap(userRepository::findByCredential)
            .orElseThrow(AuthorizationException::new);
        user.logOut();
        return UserDtoFactory.createDto(user);
    }

    private Credential getCredential(TokenResponse tokenResponse, GoogleIdToken.Payload tokenPayload) {
        CredentialId credentialId = CredentialId.oauth2("GOOGLE", tokenPayload.getSubject());
        return credentialRepository.find(credentialId)
            .orElseGet(() -> credentialRepository.add(new Credential(credentialId)))
            .apply(tokenResponse.getAccessToken(), tokenResponse.getRefreshToken(), calculateExpiration(tokenResponse.getExpiresInSeconds()));
    }

    private User getUser(GoogleIdToken.Payload tokenPayload) {
        UserId userId = UserId.email(tokenPayload.getEmail());
        User user = userRepository.find(userId).orElseGet(() -> userRepository.add(new User(userId)));
        user.setAttribute(UserAttribute.EMAIL, tokenPayload.getEmail());
        user.setAttribute(UserAttribute.AVATAR, String.class.cast(tokenPayload.get("picture")));
        return user;
    }

    private static OffsetDateTime calculateExpiration(Long expiresInSeconds) {
        return expiresInSeconds == null ? OffsetDateTime.MAX : OffsetDateTime.now().plusSeconds(expiresInSeconds);
    }
}
