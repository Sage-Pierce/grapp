package com.wisegas.itemmanagement.service.api;

import com.wisegas.itemmanagement.service.dto.ItemDto;
import com.wisegas.itemmanagement.service.dto.ItemLineageDto;

import java.util.List;

public interface ItemService {
   ItemDto createGeneralItem(String codeType, String code, String name);

   ItemDto createSubItem(String superItemCode, String codeType, String code, String name);

   List<ItemLineageDto> getAll();

   List<ItemDto> getGeneralItems();

   ItemDto get(String primaryCode);

   ItemDto makeGeneral(String primaryCode);

   ItemDto move(String primaryCode, String superItemCode);

   void delete(String primaryCode);
}
