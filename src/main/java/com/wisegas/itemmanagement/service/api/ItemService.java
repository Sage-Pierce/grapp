package com.wisegas.itemmanagement.service.api;

import com.wisegas.itemmanagement.service.dto.ItemDto;
import com.wisegas.itemmanagement.service.dto.ItemHierarchyDto;

import java.util.List;

public interface ItemService {
   ItemDto createGeneralItem(String codeType, String code, String name);

   ItemDto createSubItem(String superItemId, String codeType, String code, String name);

   List<ItemHierarchyDto> getAll();

   List<ItemDto> getGeneralItems();

   ItemDto get(String id);

   void delete(String id);
}
