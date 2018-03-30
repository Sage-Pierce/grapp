package org.codegas.security.service.api;

import java.net.URI;

import org.codegas.security.service.dto.AuthenticatedUserDto;
import org.codegas.security.service.dto.UserDto;

public interface AuthorizationService {

    URI authorize(String redirectUri);

    AuthenticatedUserDto logIn(String redirectUri, String authCode) throws AuthorizationException;

    UserDto authenticate(Authorization<String> authorization) throws AuthorizationException;

    UserDto logOut(Authorization<String> authorization) throws AuthorizationException;
}
