package org.codegas.itemmanagement.service_impl.factory;

import java.util.stream.Collectors;

import org.codegas.itemmanagement.domain.entity.Item;
import org.codegas.itemmanagement.service.dto.ItemDto;

public final class ItemDtoFactory {

    private ItemDtoFactory() {

    }

    public static ItemDto createDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setPrimaryCode(item.getPrimaryCode().toString());
        itemDto.setName(item.getName());
        itemDto.setLineage(item.getLineage().stream().map(Item::toCodeName).collect(Collectors.toList()));
        itemDto.setSubItems(item.getSubItems().stream().map(ItemDtoFactory::createDto).collect(Collectors.toList()));
        return itemDto;
    }
}
