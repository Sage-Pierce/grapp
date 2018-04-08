package org.codegas.security.service_impl.factory;

import org.codegas.security.domain.entity.Credential;
import org.codegas.security.domain.entity.User;
import org.codegas.security.domain.entity.UserCredential;
import org.codegas.security.service.dto.AuthenticatedUserDto;

public final class AuthenticatedUserDtoFactory {

    private AuthenticatedUserDtoFactory() {

    }

    public static AuthenticatedUserDto createDto(UserCredential userCredential) {
        return createDto(userCredential.getUser(), userCredential.getCredential());
    }

    public static AuthenticatedUserDto createDto(User user, Credential credential) {
        AuthenticatedUserDto authenticatedUserDto = UserDtoFactory.createDto(user, AuthenticatedUserDto::new);
        authenticatedUserDto.setAccessToken(credential.getAccessToken());
        return authenticatedUserDto;
    }
}
