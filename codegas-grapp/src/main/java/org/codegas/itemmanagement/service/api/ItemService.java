package org.codegas.itemmanagement.service.api;

import java.util.List;

import org.codegas.itemmanagement.service.dto.ItemDto;
import org.codegas.itemmanagement.service.dto.ItemLineageDto;

public interface ItemService {

    ItemDto createGeneralItem(String codeType, String code, String name);

    ItemDto createSubItem(String superItemCode, String codeType, String code, String name);

    List<ItemLineageDto> get();

    List<ItemDto> getGeneralItems();

    ItemDto get(String primaryCode);

    ItemDto update(String primaryCode, String name);

    ItemDto makeGeneral(String primaryCode);

    ItemDto move(String primaryCode, String superItemCode);

    void delete(String primaryCode);
}
