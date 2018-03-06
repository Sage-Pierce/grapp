package org.codegas.common.security.spring.oauth2.google;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

public class GoogleTokenServices extends RemoteTokenServices {

    private static final String CHECK_TOKEN_ENDPOINT_URL = "https://www.googleapis.com/oauth2/v1/tokeninfo";

    private final OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails;

    private final AccessTokenConverter tokenConverter = new GoogleAccessTokenConverter();

    private final RestTemplate restTemplate = new IgnoreBadRequestRestTemplate();

    public GoogleTokenServices(OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails) {
        this.oAuth2ProtectedResourceDetails = oAuth2ProtectedResourceDetails;
    }

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        Map<String, Object> tokenValidationMap = validateToken(accessToken);

        if (tokenValidationMap.containsKey("error")) {
            logger.debug("validateToken returned error: " + tokenValidationMap.get("error"));
            throw new InvalidTokenException(accessToken);
        }

        Map<String, Object> standardizedTokenMap = transformTokenValidationToStandardTokenMap(tokenValidationMap);

        Assert.state(standardizedTokenMap.containsKey("client_id"), "Client id must be present in response from auth server");
        return tokenConverter.extractAuthentication(standardizedTokenMap);
    }

    private Map<String, Object> validateToken(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", createClientAuthorizationHeader());
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("token", accessToken);
        return exchangePost(CHECK_TOKEN_ENDPOINT_URL + "?access_token=" + accessToken, headers, formData);
    }

    private String createClientAuthorizationHeader() {
        try {
            String clientAuthorization = String.format("%s:%s", oAuth2ProtectedResourceDetails.getClientId(), oAuth2ProtectedResourceDetails.getClientSecret());
            return "Basic " + new String(Base64.encode(clientAuthorization.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Could not convert String");
        }
    }

    private Map<String, Object> exchangePost(String path, HttpHeaders headers, MultiValueMap<String, String> formData) {
        if (headers.getContentType() == null) {
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }
        return restTemplate.exchange(path, HttpMethod.POST, new HttpEntity<>(formData, headers), new ParameterizedTypeReference<Map<String, Object>>() {

        }).getBody();
    }

    private Map<String, Object> transformTokenValidationToStandardTokenMap(Map<String, Object> tokenValidationMap) {
        Map<String, Object> standardTokenMap = new HashMap<>(tokenValidationMap);
        standardTokenMap.put("client_id", tokenValidationMap.get("issued_to")); // Google sends 'client_id' as 'issued_to'
        standardTokenMap.put("user_name", tokenValidationMap.get("user_id"));   // Google sends 'user_name' as 'user_id'
        return standardTokenMap;
    }

    private static class IgnoreBadRequestRestTemplate extends RestTemplate {

        public IgnoreBadRequestRestTemplate() {
            setErrorHandler(new DefaultResponseErrorHandler() {

                @Override
                public void handleError(ClientHttpResponse response) throws IOException {
                    if (response.getStatusCode() != HttpStatus.BAD_REQUEST) {
                        super.handleError(response);
                    }
                }
            });
        }
    }
}
