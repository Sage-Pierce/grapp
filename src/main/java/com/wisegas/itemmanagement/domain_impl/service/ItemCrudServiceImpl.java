package com.wisegas.itemmanagement.domain_impl.service;

import com.wisegas.common.lang.exception.EntityConflictException;
import com.wisegas.itemmanagement.domain.entity.Item;
import com.wisegas.itemmanagement.domain.repository.ItemRepository;
import com.wisegas.itemmanagement.domain.service.ItemCreationService;
import com.wisegas.itemmanagement.domain.value.Code;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.stream.Collectors;

@Named
@Singleton
public class ItemCrudServiceImpl implements ItemCreationService {

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
   public Item createSubItem(Code superItemId, Code code, String name) {
      assertItemCodeUniqueness(code);
      assertItemNameUniqueness(name);
      Item superItem = itemRepository.get(superItemId);
      return superItem.addSubItem(code, name);
   }

   private void assertItemCodeUniqueness(Code code) {
      Optional<Item> foundItem = itemRepository.findByCode(code);
      if (foundItem.isPresent()) {
         throw new EntityConflictException("An Item with this code already exists: " + code + ", at " + stringifyItemHierarchy(foundItem.get()));
      }
   }

   private void assertItemNameUniqueness(String name) {
      Optional<Item> foundItem = itemRepository.findByName(name);
      if (foundItem.isPresent()) {
         throw new EntityConflictException("An Item with this name already exists: " + name + ", at " + stringifyItemHierarchy(foundItem.get()));
      }
   }

   private String stringifyItemHierarchy(Item item) {
      return item.getHierarchy().stream().map(Item::getName).collect(Collectors.joining(" -> "));
   }
}
