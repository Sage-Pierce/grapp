package com.wisegas.grapp.domain.repository;

import com.wisegas.persistence.jpa.entity.SimpleEntity;
import com.wisegas.persistence.jpa.value.EntityID;

public interface GenericEntityRepository<T extends SimpleEntity> {
   T add(T t);

   T findByID(EntityID entityID);

   T remove(T t);
}
