package org.codegas.stores.service_impl.api;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.lang.annotation.ApplicationService;
import org.codegas.commons.lang.annotation.Transactional;
import org.codegas.commons.lang.value.CodeName;
import org.codegas.stores.domain.entity.Node;
import org.codegas.stores.domain.entity.NodeItem;
import org.codegas.stores.domain.entity.StoreLayout;
import org.codegas.stores.domain.repository.StoreLayoutRepository;
import org.codegas.stores.domain.value.NodeType;
import org.codegas.stores.domain.value.StoreLayoutId;
import org.codegas.stores.service.adapter.ItemLineagesAdapter;
import org.codegas.stores.service.api.ShoppingLayoutService;
import org.codegas.stores.service.dto.ItemLineageDto;
import org.codegas.stores.service.dto.ShoppingItemDto;
import org.codegas.stores.service.dto.ShoppingLayoutDto;
import org.codegas.stores.service.dto.ShoppingListDto;
import org.codegas.stores.service.dto.ShoppingNodeDto;
import org.codegas.stores.service.value.ShoppingItemType;
import org.codegas.stores.service_impl.factory.ShoppingItemDtoFactory;
import org.codegas.stores.service_impl.factory.ShoppingLayoutDtoFactory;
import org.codegas.stores.service_impl.factory.ShoppingNodeDtoFactory;

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
    public ShoppingLayoutDto getShoppingLayout(String id, ShoppingListDto shoppingListDto) {
        List<ItemLineageDto> itemLineages = itemLineagesAdapter.getItemLineages();
        StoreLayout storeLayout = storeLayoutRepository.get(StoreLayoutId.fromString(id));
        return ShoppingLayoutDtoFactory.createDto(storeLayout, generateShoppingNodes(storeLayout, shoppingListDto.getItems(), itemLineages));
    }

    private List<ShoppingNodeDto> generateShoppingNodes(StoreLayout storeLayout, List<CodeName> items, List<ItemLineageDto> itemLineages) {
        Map<String, List<String>> lineagesByCode = createItemCodeLineageMap(itemLineages);
        Map<Node, List<ShoppingItemDto>> shoppingItemsByNode = items.stream()
            .filter(item -> lineagesByCode.containsKey(item.getCode()))
            .map(item -> findShoppingItemNode(storeLayout.getNodes(), item, lineagesByCode.get(item.getCode())))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(groupingBy(ShoppingNodeItem::getNode, mapping(ShoppingNodeItem::getItem, toList())));
        shoppingItemsByNode.computeIfAbsent(storeLayout.getNodeOfType(NodeType.ENTRANCE), (key) -> Collections.emptyList());
        shoppingItemsByNode.computeIfAbsent(storeLayout.getNodeOfType(NodeType.CHECKOUT), (key) -> Collections.emptyList());
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

        public ShoppingNodeItem(Node node, CodeName item, ShoppingItemType type) {
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
