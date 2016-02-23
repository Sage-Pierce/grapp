package com.wisegas.grapp.domain.entity

import com.wisegas.value.GeoPoint
import spock.lang.Specification

class GrappUserTest extends Specification {

   def "Adding a GrappStore to a GrappUser sets the owner on that GrappStore"() {
      given:
      GrappUser grappUser = new GrappUser()

      when:
      GrappStore grappStore = grappUser.addGrappStore("Test Store", new GeoPoint(0, 0))

      then:
      grappUser.getGrappStores().size() == 1
      grappUser.getGrappStores()[0] == grappStore
      grappStore.getOwner() == grappUser
      grappStore.getLocation() == new GeoPoint(0, 0)
   }
}
