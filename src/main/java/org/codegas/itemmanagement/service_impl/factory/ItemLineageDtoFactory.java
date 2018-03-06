package org.codegas.itemmanagement.service_impl.factory;

import java.util.stream.Collectors;

import org.codegas.itemmanagement.domain.entity.Item;
import org.codegas.itemmanagement.service.dto.ItemLineageDto;

public final class ItemLineageDtoFactory {

    private ItemLineageDtoFactory() {

    }

    public static ItemLineageDto createDto(Item item) {
        ItemLineageDto itemLineageDto = new ItemLineageDto();
        itemLineageDto.setPrimaryCode(item.getPrimaryCode().toString());
        itemLineageDto.setName(item.getName());
        itemLineageDto.setLineage(item.getLineage().stream().map(Item::toCodeName).collect(Collectors.toList()));
        return itemLineageDto;
    }
}
