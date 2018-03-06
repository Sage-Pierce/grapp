package org.codegas.shoppinglists.domain.value;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

import org.codegas.common.lang.value.AbstractId;

@Embeddable
public class ShoppingListId extends AbstractId {

    @Basic
    private String id;

    public static ShoppingListId generate() {
        return new ShoppingListId(generateValue());
    }

    public static ShoppingListId fromString(String string) {
        return new ShoppingListId(string);
    }

    protected ShoppingListId() {

    }

    private ShoppingListId(String id) {
        this.id = id;
    }

    @Override
    public Object idHash() {
        return id;
    }
}
