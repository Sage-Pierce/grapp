package org.codegas.security.service_impl.api_impl;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.ws.rs.core.UriBuilder;

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

@Transactional
public class StaticAuthService extends AbstractAuthService {

    private static final String AUTH_CODE = UUID.randomUUID().toString();

    private final String userEmail;

    public StaticAuthService(CredentialRepository credentialRepository, UserRepository userRepository, String userEmail) {
        super(credentialRepository, userRepository);
        this.userEmail = userEmail;
    }

    @Override
    public URI authorize(URI callbackUri, URI appStateUri) {
        return UriBuilder.fromUri(callbackUri)
            .queryParam("state", appStateUri.toString())
            .queryParam("code", AUTH_CODE)
            .build();
    }

    @Override
    public Authorization<String> logIn(URI callbackUri, String authCode) throws AuthorizationException {
        validateAuthCode(authCode);

        Credential credential = getCredential();
        User user = getUser();
        user.refreshCredential(credential);

        return new Authorization<>("Bearer", credential.getAccessToken());
    }

    @Override
    public Authorization<String> reauthenticate(Authorization<String> authorization) throws AuthorizationException {
        return credentialRepository.findByAccessToken(authorization.getToken())
            .map(StaticAuthService::refresh)
            .map(credential -> new Authorization<>("Bearer", credential.getAccessToken()))
            .orElseThrow(AuthorizationException::new);
    }

    private Credential getCredential() {
        CredentialId credentialId = CredentialId.oauth2("STATIC", userEmail);
        return credentialRepository.find(credentialId)
            .map(StaticAuthService::refresh)
            .orElseGet(() -> credentialRepository.add(refresh(new Credential(credentialId))));
    }

    private User getUser() {
        UserId userId = UserId.email(userEmail);
        User user = userRepository.find(userId).orElseGet(() -> userRepository.add(new User(userId)));
        user.setAttribute(UserAttribute.EMAIL, userEmail);
        return user;
    }

    private static void validateAuthCode(String authCode) throws AuthorizationException {
        if (!Objects.equals(AUTH_CODE, authCode)) {
            throw new AuthorizationException();
        }
    }

    private static Credential refresh(Credential credential) {
        return credential.apply(AUTH_CODE, AUTH_CODE, OffsetDateTime.now().plusHours(1L));
    }
}
