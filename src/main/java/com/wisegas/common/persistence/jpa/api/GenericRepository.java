package com.wisegas.common.persistence.jpa.api;

import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.common.persistence.jpa.value.EntityIdFuck;

import java.util.List;

public interface GenericRepository<T extends SimpleEntity> {
   T add(T t);

   List<T> getAll();

   T get(EntityIdFuck entityIdFuck);

   T remove(T t);
}
