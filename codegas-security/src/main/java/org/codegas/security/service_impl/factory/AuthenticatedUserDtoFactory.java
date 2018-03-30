package org.codegas.security.service_impl.factory;

import org.codegas.security.domain.entity.UserCredential;
import org.codegas.security.service.dto.AuthenticatedUserDto;

public final class AuthenticatedUserDtoFactory {

    private AuthenticatedUserDtoFactory() {

    }

    public static AuthenticatedUserDto createDto(UserCredential userCredential) {
        AuthenticatedUserDto authenticatedUserDto = UserDtoFactory.createDto(userCredential.getUser(), AuthenticatedUserDto::new);
        authenticatedUserDto.setAccessToken(userCredential.getCredential().getAccessToken());
        return authenticatedUserDto;
    }
}
