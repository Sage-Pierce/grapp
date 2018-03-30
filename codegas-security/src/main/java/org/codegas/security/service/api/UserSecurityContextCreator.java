package org.codegas.security.service.api;

import java.util.function.BiFunction;

import javax.ws.rs.core.SecurityContext;

import org.codegas.security.service.dto.UserDto;

public interface UserSecurityContextCreator extends BiFunction<Authentication, UserDto, SecurityContext> {

    default SecurityContext unauthenticated() {
        return new UnauthenticatedSecurityContext();
    }
}
