package org.codegas.security.service.dto;

import java.util.Collection;
import java.util.Map;

import org.codegas.commons.lang.value.AbstractDto;

public class UserDto extends AbstractDto {

    private Map<String, String> attributes;

    private Collection<String> roles;

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }
}
