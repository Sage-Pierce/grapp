package org.codegas.itemmanagement.domain.service;

import org.codegas.itemmanagement.domain.entity.Item;
import org.codegas.itemmanagement.domain.value.Code;

public interface ItemImportService {

    Item importGeneralItem(Code code, String name);

    Item importSubItem(Code superCode, Code code, String name);
}
