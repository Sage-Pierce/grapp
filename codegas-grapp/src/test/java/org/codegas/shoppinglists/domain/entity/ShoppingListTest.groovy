package org.codegas.shoppinglists.domain.entity

import org.codegas.shoppinglists.domain.value.Item
import org.codegas.shoppinglists.test.builder.ShoppingListBuilder
import spock.lang.Specification

class ShoppingListTest extends Specification {

   def "A Shopping List can only have one of a certain Item"() {
      given:
      ShoppingList shoppingList = ShoppingListBuilder.build()

      and:
      Item item = new Item("ITEM", "Test Item")

      when: "The same Item is added twice"
      shoppingList.addItem(item)
      shoppingList.addItem(item)

      then:
      shoppingList.getItems().size() == 1
   }
}
