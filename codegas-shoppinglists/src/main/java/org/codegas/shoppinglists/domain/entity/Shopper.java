package org.codegas.shoppinglists.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codegas.commons.domain.entity.DomainEntity;
import org.codegas.commons.lang.value.PrincipalName;

@Entity
public class Shopper extends DomainEntity<PrincipalName> {

    @Id
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "shopper", orphanRemoval = true)
    private List<ShoppingList> lists = new ArrayList<>();

    public Shopper(PrincipalName name) {
        this.name = name.toString();
    }

    protected Shopper() {

    }

    @Override
    public PrincipalName getId() {
        return getName();
    }

    public PrincipalName getName() {
        return PrincipalName.fromString(name);
    }

    public List<ShoppingList> getLists() {
        return lists;
    }

    public ShoppingList addList(String name) {
        ShoppingList list = new ShoppingList(this, name);
        lists.add(list);
        return list;
    }
}
