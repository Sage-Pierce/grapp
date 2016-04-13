package com.wisegas.grapp.storemanagement.test.builders

import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreLayout

class GrappStoreLayoutBuilder {

   static GrappStoreLayout grappStoreLayout() {
      GrappStoreBuilder.grappStore().getGrappStoreLayout()
   }
}
