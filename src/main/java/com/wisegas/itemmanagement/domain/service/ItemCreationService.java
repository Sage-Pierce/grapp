package com.wisegas.itemmanagement.domain.service;

import com.wisegas.itemmanagement.domain.entity.Item;
import com.wisegas.itemmanagement.domain.value.Code;

public interface ItemCreationService {
   Item createGeneralItem(Code code, String name);

   Item createSubItem(Code superItemId, Code code, String name);
}
