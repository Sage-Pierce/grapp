package org.codegas.shoppinglists.domain.value;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

import org.codegas.commons.lang.value.AbstractId;

@Embeddable
public class ShoppingListItemId extends AbstractId {

    @Basic
    private String id;

    public static ShoppingListItemId generate() {
        return new ShoppingListItemId(generateValue());
    }

    public static ShoppingListItemId fromString(String string) {
        return new ShoppingListItemId(string);
    }

    protected ShoppingListItemId() {

    }

    private ShoppingListItemId(String id) {
        this.id = id;
    }

    @Override
    public Object idHash() {
        return id;
    }
}
