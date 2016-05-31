package com.wisegas.stores.service_impl.api_impl

import com.wisegas.common.lang.spacial.GeoPoint
import com.wisegas.common.lang.spacial.GeoPolygon
import com.wisegas.common.lang.value.CodeName
import com.wisegas.stores.domain.entity.Node
import com.wisegas.stores.domain.entity.StoreLayout
import com.wisegas.stores.domain.repository.StoreLayoutRepository
import com.wisegas.stores.domain.value.Item
import com.wisegas.stores.domain.value.NodeType
import com.wisegas.stores.service.adapter.ItemLineagesAdapter
import com.wisegas.stores.service.api.ShoppingLayoutService
import com.wisegas.stores.service.dto.ItemLineageDto
import com.wisegas.stores.service.dto.ShoppingListDto
import com.wisegas.stores.service.dto.ShoppingNodeDto
import com.wisegas.stores.service.value.ShoppingItemType
import com.wisegas.stores.test.builders.StoreLayoutBuilder
import spock.lang.Specification

class ShoppingLayoutServiceImplTest extends Specification {
   private static final CodeName edibleGrocery = new CodeName("EG", "Edible Grocery")
   private static final CodeName fruits = new CodeName("FR", "Fruits")
   private static final CodeName apples = new CodeName("AP", "Apples")

   private StoreLayout storeLayout
   private Node entrance
   private Node checkout
   private Node shoppingNode

   private ShoppingLayoutService shoppingLayoutService

   def setup() {
      List<ItemLineageDto> itemLineages = [
         new ItemLineageDto(item: edibleGrocery, lineage: [edibleGrocery]),
         new ItemLineageDto(item: fruits, lineage: [fruits, edibleGrocery]),
         new ItemLineageDto(item: apples, lineage: [apples, fruits, edibleGrocery])
      ]

      storeLayout = StoreLayoutBuilder.storeLayout()
      storeLayout.setOuterOutline(new GeoPolygon([new GeoPoint(4d, 4d), new GeoPoint(-4d, 4d), new GeoPoint(-4d, -4d), new GeoPoint(4d, -4d)]))
      storeLayout.setInnerOutline(new GeoPolygon([new GeoPoint(3d, 3d), new GeoPoint(-3d, 3d), new GeoPoint(-3d, -3d), new GeoPoint(3d, -3d)]))
      storeLayout.addFeature(new GeoPolygon([new GeoPoint(1d, 1d), new GeoPoint(-1d, 1d), new GeoPoint(-1d, -1d), new GeoPoint(1d, -1d)]))
      entrance = storeLayout.addNode(NodeType.ENTRANCE, new GeoPoint(-2d, -2d))
      checkout = storeLayout.addNode(NodeType.CHECKOUT, new GeoPoint(2d, 2d))
      shoppingNode = storeLayout.addNode(NodeType.REGULAR, new GeoPoint(-2d, 2d))
      shoppingNode.addItem(new Item(fruits.getCode(), fruits.getName()))

      shoppingLayoutService = new ShoppingLayoutServiceImpl(
         Mock(ItemLineagesAdapter) { getItemLineages() >> itemLineages },
         Mock(StoreLayoutRepository) { get(storeLayout.getId()) >> storeLayout }
      )
   }

   def "The Shopping Layout for an empty Shopping List is the Store Layout and its entrance and checkout"() {
      given: "An empty Shopping List"
      ShoppingListDto shoppingListDto = new ShoppingListDto(items: [])

      when:
      def result = shoppingLayoutService.getShoppingLayout(storeLayout.getId().toString(), shoppingListDto)

      then:
      result.getId() == storeLayout.getId().toString()
      result.getOuterOutline() == storeLayout.getOuterOutline()
      result.getInnerOutline() == storeLayout.getInnerOutline()
      result.getFeatures().collect { it.getPolygon() } == storeLayout.getFeatures().collect { it.getPolygon() }
      result.getNodes().size() == 2
      result.getNodes().collect { it.getId() }.containsAll([entrance.getId().toString(), checkout.getId().toString()])
   }

   def "The Shopping Layout for Items explicitly mapped contains the entrance, checkout, and Node where Items are located"() {
      given: "A Shopping List with an Item explicitly mapped in the Layout"
      ShoppingListDto shoppingListDto = new ShoppingListDto(items: [fruits])

      when:
      def result = shoppingLayoutService.getShoppingLayout(storeLayout.getId().toString(), shoppingListDto)

      then:
      result.getNodes().size() == 3

      and:
      ShoppingNodeDto shoppingNodeDto = result.getNodes().find { it.getId() == shoppingNode.getId().toString() }

      and:
      shoppingNodeDto.getItems().size() == 1
      shoppingNodeDto.getItems()[0].getItem() == fruits
      shoppingNodeDto.getItems()[0].getType() == ShoppingItemType.EXPLICIT.name()
   }

   def "The Shopping Layout for Items implicitly mapped contains the entrance, checkout, and Node where Items are located"() {
      given: "A Shopping List with an Item implicitly mapped in the Layout"
      ShoppingListDto shoppingListDto = new ShoppingListDto(items: [apples])

      when:
      def result = shoppingLayoutService.getShoppingLayout(storeLayout.getId().toString(), shoppingListDto)

      then:
      result.getNodes().size() == 3

      and:
      ShoppingNodeDto shoppingNodeDto = result.getNodes().find { it.getId() == shoppingNode.getId().toString() }

      and:
      shoppingNodeDto.getItems().size() == 1
      shoppingNodeDto.getItems()[0].getItem() == apples
      shoppingNodeDto.getItems()[0].getType() == ShoppingItemType.IMPLICIT.name()
   }

   def "The Shopping Layout for Items not mapped contains only the entrance and checkout"() {
      given: "A Shopping List with an Item not mapped in the Layout"
      ShoppingListDto shoppingListDto = new ShoppingListDto(items: [edibleGrocery])

      when:
      def result = shoppingLayoutService.getShoppingLayout(storeLayout.getId().toString(), shoppingListDto)

      then:
      result.getNodes().size() == 2
      result.getNodes().collect { it.getId() }.containsAll([entrance.getId().toString(), checkout.getId().toString()])
   }
}
