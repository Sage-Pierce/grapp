package com.wisegas.grapp.itemmanagement.domain.service;

import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem;
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemId;

public interface GrappItemCreationService {
   GrappItem createGeneralItem(String name);

   GrappItem createSubItem(GrappItemId superItemId, String name);
}
