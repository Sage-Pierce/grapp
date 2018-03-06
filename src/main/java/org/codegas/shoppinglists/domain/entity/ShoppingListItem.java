package org.codegas.shoppinglists.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.codegas.common.domain.entity.SimpleEntity;
import org.codegas.shoppinglists.domain.value.Item;
import org.codegas.shoppinglists.domain.value.ShoppingListItemId;

@Entity
public class ShoppingListItem extends SimpleEntity<ShoppingListItemId> {

    @EmbeddedId
    private ShoppingListItemId id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    private ShoppingList shoppingList;

    private Item item;

    private boolean obtained;

    public ShoppingListItem(ShoppingList shoppingList, Item item) {
        id = ShoppingListItemId.generate();
        setShoppingList(shoppingList);
        setItem(item);
    }

    protected ShoppingListItem() {

    }

    @Override
    public ShoppingListItemId getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public boolean isObtained() {
        return obtained;
    }

    public void setObtained(boolean obtained) {
        this.obtained = obtained;
    }

    private void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    private void setItem(Item item) {
        this.item = item;
    }
}
