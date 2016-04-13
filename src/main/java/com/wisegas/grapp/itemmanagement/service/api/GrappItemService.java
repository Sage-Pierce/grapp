package com.wisegas.grapp.itemmanagement.service.api;

import com.wisegas.grapp.itemmanagement.service.dto.GrappItemDTOO;

import java.util.List;

public interface GrappItemService {
   GrappItemDTOO createGeneralItem(String codeType, String code, String name);

   GrappItemDTOO createSubItem(String superItemId, String codeType, String code, String name);

   List<GrappItemDTOO> getAll();

   List<GrappItemDTOO> getGeneralItems();

   GrappItemDTOO get(String id);

   void delete(String id);
}
