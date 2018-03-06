package org.codegas.shoppinglists.domain.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import org.codegas.commons.persistence.jpa.entity.NamedEntity;
import org.codegas.shoppinglists.domain.value.Item;
import org.codegas.shoppinglists.domain.value.ShoppingListId;

@Entity
public class ShoppingList extends NamedEntity<ShoppingListId> {

    @EmbeddedId
    private ShoppingListId id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    private Shopper shopper;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shoppingList", orphanRemoval = true)
    @MapKey(name = "item")
    private Map<Item, ShoppingListItem> items = new HashMap<>();

    public ShoppingList(Shopper shopper, String name) {
        id = ShoppingListId.generate();
        setName(name);
        setShopper(shopper);
    }

    protected ShoppingList() {

    }

    @Override
    public ShoppingListId getId() {
        return id;
    }

    public Collection<ShoppingListItem> getItems() {
        return items.values();
    }

    public ShoppingListItem addItem(Item item) {
        return items.computeIfAbsent(item, (key) -> new ShoppingListItem(this, item));
    }

    private void setShopper(Shopper shopper) {
        this.shopper = shopper;
    }
}
