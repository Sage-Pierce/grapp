package com.wisegas.itemmanagement.domain.service;

import com.wisegas.itemmanagement.domain.entity.Item;
import com.wisegas.itemmanagement.domain.value.Code;

public interface ItemImportService {
   Item importGeneralItem(Code code, String name);

   Item importSubItem(Code superCode, Code code, String name);
}
