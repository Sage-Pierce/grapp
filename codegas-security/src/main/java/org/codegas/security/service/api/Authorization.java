package org.codegas.security.service.api;

import java.util.function.Function;

public final class Authorization<T> {

    private final String scheme;

    private final T token;

    public Authorization(String scheme, T token) {
        this.scheme = scheme;
        this.token = token;
    }

    public static Authorization<String> parse(String authorization) {
        return parse(validate(authorization), Function.identity());
    }

    public static <T> Authorization<T> parse(String authorization, Function<? super String, T> tokenParser) {
        String[] splitAuthorization = validate(authorization).split("\\s+", 2);
        return new Authorization<>(splitAuthorization[0], tokenParser.apply(splitAuthorization[1]));
    }

    @Override
    public String toString() {
        return scheme + " " + token;
    }

    public String getScheme() {
        return scheme;
    }

    public T getToken() {
        return token;
    }

    private static String validate(String authorization) {
        if (authorization == null || authorization.isEmpty()) {
            throw new IllegalArgumentException("Invalid authorization=" + authorization);
        }
        return authorization;
    }
}
