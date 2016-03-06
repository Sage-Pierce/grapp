package com.wisegas.persistence.jpa.api;

import com.wisegas.persistence.jpa.entity.SimpleEntity;
import com.wisegas.persistence.jpa.value.EntityID;

import java.util.List;

public interface GenericRepository<T extends SimpleEntity> {
   T add(T t);

   List<T> getAll();

   T findByID(EntityID entityID);

   T remove(T t);
}
