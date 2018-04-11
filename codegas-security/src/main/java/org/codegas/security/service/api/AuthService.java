package org.codegas.security.service.api;

import java.net.URI;

import org.codegas.security.service.dto.UserDto;

public interface AuthService {

    URI authorize(URI callbackUri, URI appStateUri);

    Authorization<String> logIn(URI callbackUri, String authCode) throws AuthorizationException;

    Authorization<String> reauthenticate(Authorization<String> authorization) throws AuthorizationException;

    UserDto authenticate(Authorization<String> authorization) throws AuthorizationException;

    UserDto logOut(Authorization<String> authorization) throws AuthorizationException;
}
