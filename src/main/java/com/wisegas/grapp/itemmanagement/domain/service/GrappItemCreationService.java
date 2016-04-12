package com.wisegas.grapp.itemmanagement.domain.service;

import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem;
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCode;

public interface GrappItemCreationService {
   GrappItem createGeneralItem(GrappItemCode code, String name);

   GrappItem createSubItem(GrappItemCode superItemId, GrappItemCode code, String name);
}
