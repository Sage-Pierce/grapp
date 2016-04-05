package com.wisegas.grapp.service.api;

import com.wisegas.grapp.service.dto.GrappItemDTO;

import java.util.List;

public interface GrappItemService {
   GrappItemDTO createGeneralItem(String name);

   GrappItemDTO createSubItem(String superItemId, String name);

   List<GrappItemDTO> getAll();

   List<GrappItemDTO> getGeneralItems();

   GrappItemDTO get(String id);

   GrappItemDTO update(String id, String name);

   void delete(String id);
}
