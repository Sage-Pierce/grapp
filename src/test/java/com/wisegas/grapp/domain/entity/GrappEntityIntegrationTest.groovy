package com.wisegas.grapp.domain.entity

import com.wisegas.grapp.test.Builders;
import com.wisegas.persistence.jpa.entity.EntityIntegrationTest;
import com.wisegas.persistence.jpa.entity.SimpleEntity

import java.lang.reflect.ParameterizedType;

abstract class GrappEntityIntegrationTest<T extends SimpleEntity> extends EntityIntegrationTest<T> {

   @Override
   T createTestEntity() {
      String entityName = ((Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName()
      String entityBuilderMethodName = "${Character.toLowerCase(entityName.charAt(0))}${entityName.substring(1)}"
      Builders."$entityBuilderMethodName"()
   }
}
