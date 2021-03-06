package org.codegas.itemmanagement.domain.value;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.codegas.commons.lang.value.AbstractId;

@Embeddable
public class Code extends AbstractId {

    private static final String TYPE_VALUE_DELIMITER = ":";

    @Enumerated(EnumType.STRING)
    private CodeType type;

    private String value;

    public Code(CodeType type, String value) {
        this.type = type;
        this.value = value;
    }

    protected Code() {

    }

    public static Code random() {
        return new Code(CodeType.RANDOM, generateValue());
    }

    public static Code fromString(String string) {
        String[] split = string.split(TYPE_VALUE_DELIMITER);
        return new Code(CodeType.valueOf(split[0]), split[1]);
    }

    @Override
    public String toString() {
        return type.name() + TYPE_VALUE_DELIMITER + value;
    }

    @Override
    public Object idHash() {
        return type + value;
    }
}
