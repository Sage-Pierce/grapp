package org.codegas.security.service_impl.api_impl;

import javax.ws.rs.core.SecurityContext;

import org.codegas.security.service.api.AuthenticatedSecurityContext;
import org.codegas.security.service.api.Authentication;
import org.codegas.security.service.api.UserPrincipal;
import org.codegas.security.service.api.UserSecurityContextCreator;
import org.codegas.security.service.dto.UserDto;

public class DefaultUserSecurityContextCreator implements UserSecurityContextCreator {

    protected final String principalNameAttribute;

    public DefaultUserSecurityContextCreator(String principalNameAttribute) {
        this.principalNameAttribute = principalNameAttribute;
    }

    @Override
    public SecurityContext apply(Authentication authentication, UserDto userDto) {
        return new AuthenticatedSecurityContext(authentication, createUserPrincipal(userDto));
    }

    protected UserPrincipal createUserPrincipal(UserDto userDto) {
        return new UserPrincipal(userDto.getAttribute(principalNameAttribute), userDto.getRoles());
    }
}
