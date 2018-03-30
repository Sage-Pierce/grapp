package org.codegas.security.service.api;

import java.net.URI;

import javax.ws.rs.core.SecurityContext;

import org.codegas.security.service.dto.AuthenticatedUserDto;

public interface AuthorizationService {

    URI authorize(String redirectUri);

    AuthenticatedUserDto logIn(String redirectUri, String authCode);

    SecurityContext authenticate(String requestScheme, String authorization);
}
