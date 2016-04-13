package com.wisegas.grapp.itemmanagement.service.api;

import com.wisegas.grapp.itemmanagement.service.dto.GrappItemDto;

import java.util.List;

public interface GrappItemService {
   GrappItemDto createGeneralItem(String codeType, String code, String name);

   GrappItemDto createSubItem(String superItemId, String codeType, String code, String name);

   List<GrappItemDto> getAll();

   List<GrappItemDto> getGeneralItems();

   GrappItemDto get(String id);

   void delete(String id);
}
