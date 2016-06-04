package com.wisegas.shoppinglists.domain.entity

import com.wisegas.shoppinglists.domain.value.Item
import com.wisegas.shoppinglists.test.builders.ShoppingListBuilder
import spock.lang.Specification

class ShoppingListTest extends Specification {

   def "A Shopping List can only have one of a certain Item"() {
      given:
      ShoppingList shoppingList = ShoppingListBuilder.shoppingList()

      and:
      Item item = new Item("ITEM", "Test Item")

      when: "The same Item is added twice"
      shoppingList.addItem(item)
      shoppingList.addItem(item)

      then:
      shoppingList.getItems().size() == 1
   }
}