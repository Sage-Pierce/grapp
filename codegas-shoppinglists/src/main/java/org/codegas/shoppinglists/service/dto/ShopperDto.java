package org.codegas.shoppinglists.service.dto;

import java.util.List;

import org.codegas.commons.lang.value.IdName;

public class ShopperDto {

    private String name;

    private List<IdName> lists;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IdName> getLists() {
        return lists;
    }

    public void setLists(List<IdName> lists) {
        this.lists = lists;
    }
}
