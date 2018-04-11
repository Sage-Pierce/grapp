package org.codegas.itemmanagement.service.dto;

import java.util.List;

public class ItemDto extends ItemLineageDto {

    private List<ItemDto> subItems;

    public List<ItemDto> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<ItemDto> subItems) {
        this.subItems = subItems;
    }
}
