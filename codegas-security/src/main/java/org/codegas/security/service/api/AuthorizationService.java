package org.codegas.security.service.api;

import org.codegas.security.service.dto.AuthenticatedUserDto;
import org.codegas.security.service.dto.UserDto;

public interface AuthorizationService {

    String authorize(String redirectUri);

    AuthenticatedUserDto logIn(String redirectUri, String authCode) throws AuthorizationException;

    AuthenticatedUserDto authenticate(Authorization<String> authorization) throws AuthorizationException;

    UserDto logOut(Authorization<String> authorization) throws AuthorizationException;
}
