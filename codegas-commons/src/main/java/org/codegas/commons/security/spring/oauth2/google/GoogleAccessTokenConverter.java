package org.codegas.commons.security.spring.oauth2.google;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

public class GoogleAccessTokenConverter extends DefaultAccessTokenConverter {

    private UserAuthenticationConverter userTokenConverter;

    public GoogleAccessTokenConverter() {
        setUserTokenConverter(new DefaultUserAuthenticationConverter());
    }

    @Override
    public final void setUserTokenConverter(UserAuthenticationConverter userTokenConverter) {
        super.setUserTokenConverter(this.userTokenConverter = userTokenConverter);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        String clientId = (String) map.get(CLIENT_ID);
        Map<String, String> requestParameters = Collections.singletonMap(CLIENT_ID, clientId);
        LinkedHashSet<String> resourceIds = new LinkedHashSet<>(map.containsKey(AUD) ? (Collection<String>) map.get(AUD) : Collections.emptySet());
        OAuth2Request request = new OAuth2Request(requestParameters, clientId, null, true, parseScopes(map), resourceIds, null, null, null);
        return new OAuth2Authentication(request, userTokenConverter.extractAuthentication(map));
    }

    private Set<String> parseScopes(Map<String, ?> map) {
        // Parsing of scopes coming back from Google are slightly different from the default implementation.
        // Instead of it being a collection it is a String where multiple scopes are separated by a space.
        Object scopeAsObject = map.containsKey(SCOPE) ? map.get(SCOPE) : "";
        if (String.class.isAssignableFrom(scopeAsObject.getClass())) {
            return new LinkedHashSet<>(Arrays.asList(scopeAsObject.toString().split(" ")));
        } else {
            return Collection.class.isAssignableFrom(scopeAsObject.getClass()) ? new LinkedHashSet<>((Collection<String>) scopeAsObject)
                : Collections.emptySet();
        }
    }
}
