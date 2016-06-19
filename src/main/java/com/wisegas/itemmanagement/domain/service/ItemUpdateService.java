package com.wisegas.itemmanagement.domain.service;

import com.wisegas.itemmanagement.domain.entity.Item;

public interface ItemUpdateService {
   void changeItemName(Item item, String name);
}
