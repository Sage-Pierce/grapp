package org.codegas.shoppinglists.service.dto;

import java.util.List;

import org.codegas.common.lang.value.IdName;

public class ShopperDto {

    private String email;

    private List<IdName> lists;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<IdName> getLists() {
        return lists;
    }

    public void setLists(List<IdName> lists) {
        this.lists = lists;
    }
}
