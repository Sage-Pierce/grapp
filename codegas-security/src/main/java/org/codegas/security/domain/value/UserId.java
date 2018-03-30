package org.codegas.security.domain.value;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.codegas.commons.lang.value.AbstractId;
import org.codegas.commons.lang.value.Email;

@Embeddable
public class UserId extends AbstractId {

    private static final String TYPE_VALUE_DELIMITER = ":";

    @Enumerated(EnumType.STRING)
    private UserIdType type;

    private String id;

    public static UserId email(String email) {
        return new UserId(UserIdType.EMAIL, Email.validate(email));
    }

    public static UserId fromString(String string) {
        String[] split = string.split(TYPE_VALUE_DELIMITER);
        return new UserId(UserIdType.valueOf(split[0]), split[1]);
    }

    protected UserId() {

    }

    private UserId(UserIdType type, String id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public String toString() {
        return type.name() + TYPE_VALUE_DELIMITER + id;
    }

    @Override
    public Object idHash() {
        return type + id;
    }
}
