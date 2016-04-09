package com.wisegas.grapp.itemmanagement.service.api;

import com.wisegas.grapp.itemmanagement.service.dto.GrappItemDTO;

import java.util.List;

public interface GrappItemService {
   GrappItemDTO createGeneralItem(String name);

   GrappItemDTO createSubItem(String superItemId, String name);

   List<GrappItemDTO> getAll();

   List<GrappItemDTO> getGeneralItems();

   GrappItemDTO get(String id);

   void delete(String id);
}
