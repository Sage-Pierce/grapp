package org.codegas.stores.test.builder;

import org.codegas.stores.domain.entity.StoreLayout;

import org.codegas.stores.domain.entity.StoreLayout;

public final class StoreLayoutBuilder {

   private StoreLayoutBuilder() {

   }

   public static StoreLayout build() {
      return StoreBuilder.build().getStoreLayout();
   }
}
