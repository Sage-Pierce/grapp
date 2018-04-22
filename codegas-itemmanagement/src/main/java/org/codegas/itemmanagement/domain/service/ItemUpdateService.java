package org.codegas.itemmanagement.domain.service;

import org.codegas.itemmanagement.domain.entity.Item;

public interface ItemUpdateService {

    void changeItemName(Item item, String name);
}
