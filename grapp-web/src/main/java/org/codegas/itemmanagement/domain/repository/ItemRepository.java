package org.codegas.itemmanagement.domain.repository;

import java.util.List;
import java.util.Optional;

import org.codegas.persistence.api.GenericRepository;
import org.codegas.itemmanagement.domain.entity.Item;
import org.codegas.itemmanagement.domain.value.Code;

public interface ItemRepository extends GenericRepository<Item> {

    List<Item> getGeneralItems();

    Optional<Item> findByName(String name);

    Optional<Item> findByCode(Code code);
}