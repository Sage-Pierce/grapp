package com.wisegas.grapp.itemmanagement.service.api;

import com.wisegas.grapp.itemmanagement.service.dto.ItemDto;

import java.util.List;

public interface ItemService {
   ItemDto createGeneralItem(String codeType, String code, String name);

   ItemDto createSubItem(String superItemId, String codeType, String code, String name);

   List<ItemDto> getAll();

   List<ItemDto> getGeneralItems();

   ItemDto get(String id);

   void delete(String id);
}
