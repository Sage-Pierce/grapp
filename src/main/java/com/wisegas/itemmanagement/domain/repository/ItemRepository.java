package com.wisegas.itemmanagement.domain.repository;

import com.wisegas.common.persistence.jpa.api.GenericRepository;
import com.wisegas.itemmanagement.domain.entity.Item;
import com.wisegas.itemmanagement.domain.value.Code;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends GenericRepository<Item> {
   List<Item> getGeneralItems();

   Optional<Item> findByName(String name);

   Optional<Item> findByCode(Code code);
}
