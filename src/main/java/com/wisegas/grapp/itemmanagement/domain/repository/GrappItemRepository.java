package com.wisegas.grapp.itemmanagement.domain.repository;

import com.wisegas.common.persistence.jpa.api.GenericRepository;
import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem;
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCode;

import java.util.List;
import java.util.Optional;

public interface GrappItemRepository extends GenericRepository<GrappItem> {
   List<GrappItem> getGeneralItems();

   Optional<GrappItem> findByName(String name);

   Optional<GrappItem> findByCode(GrappItemCode code);
}
