package org.codegas.common.security.spring.oauth2.google;

import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

public class GoogleAuthorizationCodeResourceDetails extends AuthorizationCodeResourceDetails {

    public GoogleAuthorizationCodeResourceDetails() {
        setId("google-oauth-client");
        setAccessTokenUri("https://accounts.google.com/o/oauth2/token");
        setUserAuthorizationUri("https://accounts.google.com/o/oauth2/auth");
        setUseCurrentUri(false);
        setAuthenticationScheme(AuthenticationScheme.query);
        setClientAuthenticationScheme(AuthenticationScheme.form);
    }
}
