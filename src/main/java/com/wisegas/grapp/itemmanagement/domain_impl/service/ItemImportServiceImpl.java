package com.wisegas.grapp.itemmanagement.domain_impl.service;

import com.wisegas.grapp.itemmanagement.domain.entity.Item;
import com.wisegas.grapp.itemmanagement.domain.repository.ItemRepository;
import com.wisegas.grapp.itemmanagement.domain.service.ItemImportService;
import com.wisegas.grapp.itemmanagement.domain.value.Code;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;

@Named
@Singleton
public class ItemImportServiceImpl implements ItemImportService {

   private final ItemRepository itemRepository;

   @Inject
   public ItemImportServiceImpl(ItemRepository itemRepository) {
      this.itemRepository = itemRepository;
   }

   @Override
   public Item importGeneralItem(Code code, String name) {
      Optional<Item> reimportedGeneralItem = validateReimport(code, name);
      return reimportedGeneralItem.isPresent() ? reimportedGeneralItem.get() : itemRepository.add(new Item(code, name));
   }

   @Override
   public Item importSubItem(Code superCode, Code code, String name) {
      Optional<Item> reimportedSubItem = validateReimport(code, name);
      return reimportedSubItem.isPresent() ? reimportedSubItem.get() : importSubItemToSuperItem(superCode, code, name);
   }

   private Item importSubItemToSuperItem(Code superCode, Code code, String name) {
      Optional<Item> foundSuperItem = itemRepository.findByCode(superCode);
      if (foundSuperItem.isPresent()) {
         return foundSuperItem.get().addSubItem(code, name);
      }
      else {
         throw new IllegalStateException("Cannot Import Sub-Item due to non-existent Super Item: " + superCode + ", " + name);
      }
   }

   private Optional<Item> validateReimport(Code code, String name) {
      Optional<Item> foundItemByCode = itemRepository.findByCode(code);
      Optional<Item> foundItemByName = itemRepository.findByName(name);
      if (!foundItemByCode.equals(foundItemByName)) {
         throw new IllegalStateException("Cannot Import conflicting Items: " + foundItemByCode + ", " + foundItemByName + ", " + code + ", " + name);
      }
      else {
         return foundItemByCode.isPresent() ? foundItemByCode : foundItemByName;
      }
   }
}
