package com.wisegas.grapp.test.builders

import com.wisegas.grapp.domain.entity.GrappUser
import com.wisegas.test.EntityBuilder

class GrappUserBuilder {

   static unique = 0

   static GrappUser grappUser() {
      unique++
      EntityBuilder.wrapBuilder(new GrappUser(
         "Test User ${unique}",
         "test@email${unique}.com",
         "<avatar ${unique}>"
      ))
   }
}