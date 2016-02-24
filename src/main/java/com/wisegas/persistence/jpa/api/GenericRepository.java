package com.wisegas.persistence.jpa.api;

import com.wisegas.persistence.jpa.entity.SimpleEntity;
import com.wisegas.persistence.jpa.value.EntityID;

public interface GenericRepository<T extends SimpleEntity> {
   T add(T t);

   T findByID(EntityID entityID);

   T remove(T t);
}
