package org.codegas.security.service.dto;

public class AuthenticatedUserDto extends UserDto {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
