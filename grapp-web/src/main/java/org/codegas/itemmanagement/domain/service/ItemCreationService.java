package org.codegas.itemmanagement.domain.service;

import org.codegas.itemmanagement.domain.entity.Item;
import org.codegas.itemmanagement.domain.value.Code;

public interface ItemCreationService {

    Item createGeneralItem(Code code, String name);

    Item createSubItem(Code superItemCode, Code code, String name);
}
