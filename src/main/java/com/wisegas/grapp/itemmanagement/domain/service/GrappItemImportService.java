package com.wisegas.grapp.itemmanagement.domain.service;

import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem;
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCode;

public interface GrappItemImportService {
   GrappItem importGeneralItem(GrappItemCode code, String name);

   GrappItem importSubItem(GrappItemCode superCode, GrappItemCode code, String name);
}
