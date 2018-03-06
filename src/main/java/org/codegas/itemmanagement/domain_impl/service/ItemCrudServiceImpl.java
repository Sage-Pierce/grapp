package org.codegas.itemmanagement.domain_impl.service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.domain.exception.EntityConflictException;
import org.codegas.itemmanagement.domain.entity.Item;
import org.codegas.itemmanagement.domain.repository.ItemRepository;
import org.codegas.itemmanagement.domain.service.ItemCreationService;
import org.codegas.itemmanagement.domain.service.ItemUpdateService;
import org.codegas.itemmanagement.domain.value.Code;

@Named
@Singleton
public class ItemCrudServiceImpl implements ItemCreationService, ItemUpdateService {

    private final ItemRepository itemRepository;

    @Inject
    public ItemCrudServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item createGeneralItem(Code code, String name) {
        assertItemCodeUniqueness(code);
        assertItemNameUniqueness(name);
        return itemRepository.add(new Item(code, name));
    }

    @Override
    public Item createSubItem(Code superItemCode, Code code, String name) {
        assertItemCodeUniqueness(code);
        assertItemNameUniqueness(name);
        Item superItem = itemRepository.get(superItemCode);
        return superItem.addSubItem(code, name);
    }

    @Override
    public void changeItemName(Item item, String name) {
        if (!Objects.equals(item.getName(), name)) {
            assertItemNameUniqueness(name);
            item.setName(name);
        }
    }

    private void assertItemCodeUniqueness(Code code) {
        Optional<Item> foundItem = itemRepository.findByCode(code);
        if (foundItem.isPresent()) {
            throw new EntityConflictException("An Item with this code already exists: " + code + ", at " + stringifyItemLineage(foundItem.get()));
        }
    }

    private void assertItemNameUniqueness(String name) {
        Optional<Item> foundItem = itemRepository.findByName(name);
        if (foundItem.isPresent()) {
            throw new EntityConflictException("An Item with this name already exists: " + name + ", at " + stringifyItemLineage(foundItem.get()));
        }
    }

    private String stringifyItemLineage(Item item) {
        return item.getLineage().stream().map(Item::getName).collect(Collectors.joining(" <- "));
    }
}
