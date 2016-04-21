package com.wisegas.common.persistence.jpa.api;

import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.common.persistence.jpa.value.EntityId;

import java.util.List;

public interface GenericRepository<T extends SimpleEntity> {
   T add(T t);

   T remove(EntityId entityId);

   T get(EntityId entityId);

   List<T> getAll();
}
