package com.wisegas.stores.test.builder;

import com.wisegas.stores.domain.entity.StoreLayout;

public final class StoreLayoutBuilder {

   private StoreLayoutBuilder() {

   }

   public static StoreLayout build() {
      return StoreBuilder.build().getStoreLayout();
   }
}
