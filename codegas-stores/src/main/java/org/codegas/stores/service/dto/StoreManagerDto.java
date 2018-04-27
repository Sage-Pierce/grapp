package org.codegas.stores.service.dto;

import java.util.List;

public class StoreManagerDto {

    private String name;

    private List<StoreDto> stores;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StoreDto> getStores() {
        return stores;
    }

    public void setStores(List<StoreDto> stores) {
        this.stores = stores;
    }
}
