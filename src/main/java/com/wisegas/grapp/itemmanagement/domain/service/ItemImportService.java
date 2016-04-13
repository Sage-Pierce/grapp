package com.wisegas.grapp.itemmanagement.domain.service;

import com.wisegas.grapp.itemmanagement.domain.entity.Item;
import com.wisegas.grapp.itemmanagement.domain.value.Code;

public interface ItemImportService {
   Item importGeneralItem(Code code, String name);

   Item importSubItem(Code superCode, Code code, String name);
}
