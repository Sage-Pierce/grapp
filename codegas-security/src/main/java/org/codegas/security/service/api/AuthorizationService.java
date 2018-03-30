package org.codegas.security.service.api;

import java.net.URI;
import java.util.Optional;

import org.codegas.security.service.dto.AuthenticatedUserDto;
import org.codegas.security.service.dto.UserDto;

public interface AuthorizationService {

    URI authorize(String redirectUri);

    AuthenticatedUserDto logIn(String redirectUri, String authCode);

    Optional<UserDto> authenticate(Authorization<String> authorization);
}
