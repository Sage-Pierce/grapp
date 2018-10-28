package org.codegas.itemmanagement.domain.repository;

import java.util.List;
import java.util.Optional;

import org.codegas.commons.domain.persistence.Repository;
import org.codegas.itemmanagement.domain.entity.Item;
import org.codegas.itemmanagement.domain.value.Code;

public interface ItemRepository extends Repository<Item> {

    List<Item> getGeneralItems();

    Optional<Item> findByName(String name);

    Optional<Item> findByCode(Code code);
}
