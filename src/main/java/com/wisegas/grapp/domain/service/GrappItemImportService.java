package com.wisegas.grapp.domain.service;

import com.wisegas.grapp.domain.entity.GrappItem;
import com.wisegas.grapp.domain.value.GrappItemCode;

public interface GrappItemImportService {
   GrappItem importGeneralItem(GrappItemCode code, String name);

   GrappItem importSubItem(GrappItemCode superCode, GrappItemCode code, String name);

   GrappItem importSubItem(GrappItemCode superCode, String name);
}
