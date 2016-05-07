package com.wisegas.storemanagement.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.common.lang.value.CodeName;
import com.wisegas.storemanagement.domain.entity.Node;
import com.wisegas.storemanagement.domain.entity.NodeItem;
import com.wisegas.storemanagement.domain.entity.StoreLayout;
import com.wisegas.storemanagement.domain.repository.StoreLayoutRepository;
import com.wisegas.storemanagement.domain.value.NodeType;
import com.wisegas.storemanagement.domain.value.StoreLayoutId;
import com.wisegas.storemanagement.service.adapter.ItemLineagesAdapter;
import com.wisegas.storemanagement.service.api.ShoppingLayoutService;
import com.wisegas.storemanagement.service.dto.*;
import com.wisegas.storemanagement.service.value.ShoppingItemType;
import com.wisegas.storemanagement.service_impl.factory.ShoppingItemDtoFactory;
import com.wisegas.storemanagement.service_impl.factory.ShoppingLayoutDtoFactory;
import com.wisegas.storemanagement.service_impl.factory.ShoppingNodeDtoFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.*;

import static java.util.stream.Collectors.*;

@Named
@Singleton
@Transactional
@ApplicationService
public class ShoppingLayoutServiceImpl implements ShoppingLayoutService {

   private final ItemLineagesAdapter itemLineagesAdapter;
   private final StoreLayoutRepository storeLayoutRepository;

   @Inject
   public ShoppingLayoutServiceImpl(ItemLineagesAdapter itemLineagesAdapter, StoreLayoutRepository storeLayoutRepository) {
      this.itemLineagesAdapter = itemLineagesAdapter;
      this.storeLayoutRepository = storeLayoutRepository;
   }

   @Override
   public ShoppingLayoutDto getShoppingLayout(String id, ShoppingListDto shoppingList) {
      List<ItemLineageDto> itemLineages = itemLineagesAdapter.getItemLineages();
      StoreLayout storeLayout = storeLayoutRepository.get(StoreLayoutId.fromString(id));
      return ShoppingLayoutDtoFactory.createDto(storeLayout, generateShoppingNodes(storeLayout, shoppingList.getItems(), itemLineages));
   }

   private List<ShoppingNodeDto> generateShoppingNodes(StoreLayout storeLayout, List<CodeName> items, List<ItemLineageDto> itemLineages) {
      Map<String, List<String>> lineagesByCode = createItemCodeLineageMap(itemLineages);
      Map<Node, List<ShoppingItemDto>> shoppingItemsByNode =  items.stream()
                                                                   .filter(item -> lineagesByCode.containsKey(item.getCode()))
                                                                   .map(item -> findShoppingItemNode(storeLayout.getNodes(), item, lineagesByCode.get(item.getCode())))
                                                                   .filter(Optional::isPresent)
                                                                   .map(Optional::get)
                                                                   .collect(groupingBy(ShoppingNodeItem::getNode, mapping(ShoppingNodeItem::getItem, toList())));
      shoppingItemsByNode.computeIfAbsent(storeLayout.getNodeOfType(NodeType.ENTRANCE), (key) -> Collections.emptyList());
      shoppingItemsByNode.computeIfAbsent(storeLayout.getNodeOfType(NodeType.EXIT), (key) -> Collections.emptyList());
      return shoppingItemsByNode.entrySet().stream()
                                .map(entry -> ShoppingNodeDtoFactory.createDto(entry.getKey(), entry.getValue()))
                                .collect(toList());
   }

   private Map<String, List<String>> createItemCodeLineageMap(List<ItemLineageDto> itemLineages) {
      return itemLineages.stream()
                         .filter(itemLineageDto -> !itemLineageDto.getLineage().isEmpty())
                         .collect(toMap(dto -> dto.getItem().getCode(), dto -> dto.getLineage().stream().map(CodeName::getCode).collect(toList())));
   }

   private Optional<ShoppingNodeItem> findShoppingItemNode(Collection<Node> nodes, CodeName item, List<String> itemCodeLineage) {
      Map<String, Node> nodesByItemCode = nodes.stream().flatMap(node -> node.getItems().stream()).collect(toMap(nodeItem -> nodeItem.getItem().getCode(), NodeItem::getNode));
      ShoppingItemType type = ShoppingItemType.EXPLICIT;
      for (String itemCode : itemCodeLineage) {
         if (nodesByItemCode.containsKey(itemCode)) {
            return Optional.of(new ShoppingNodeItem(nodesByItemCode.get(itemCode), item, type));
         }
         type = ShoppingItemType.IMPLICIT;
      }
      return Optional.empty();
   }

   private static final class ShoppingNodeItem {

      private final Node node;
      private final ShoppingItemDto item;

      private ShoppingNodeItem(Node node, CodeName item, ShoppingItemType type) {
         this.node = node;
         this.item = ShoppingItemDtoFactory.createDto(item, type);
      }

      public Node getNode() {
         return node;
      }

      public ShoppingItemDto getItem() {
         return item;
      }
   }
}
