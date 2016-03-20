package com.wisegas.grapp.domain.service;

import com.wisegas.grapp.domain.entity.GrappItem;
import com.wisegas.grapp.domain.value.GrappItemID;

public interface GrappItemCreationService {
   GrappItem createGeneralItem(String name);

   GrappItem createSubItem(GrappItemID superItemId, String name);
}
