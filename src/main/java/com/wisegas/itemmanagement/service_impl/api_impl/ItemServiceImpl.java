package com.wisegas.itemmanagement.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.itemmanagement.domain.repository.ItemRepository;
import com.wisegas.itemmanagement.domain.service.ItemCreationService;
import com.wisegas.itemmanagement.domain.value.Code;
import com.wisegas.itemmanagement.domain.value.CodeType;
import com.wisegas.itemmanagement.service.api.ItemService;
import com.wisegas.itemmanagement.service.dto.ItemDto;
import com.wisegas.itemmanagement.service.dto.ItemHierarchyDto;
import com.wisegas.itemmanagement.service_impl.factory.ItemDtoFactory;
import com.wisegas.itemmanagement.service_impl.factory.ItemHierarchyDtoFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Singleton
@Transactional
@ApplicationService
public class ItemServiceImpl implements ItemService {

   private final ItemCreationService itemCreationService;
   private final ItemRepository itemRepository;

   @Inject
   public ItemServiceImpl(ItemCreationService itemCreationService, ItemRepository itemRepository) {
      this.itemCreationService = itemCreationService;
      this.itemRepository = itemRepository;
   }

   @Override
   public ItemDto createGeneralItem(String codeType, String code, String name) {
      return ItemDtoFactory.createDto(itemCreationService.createGeneralItem(createItemCode(codeType, code), name));
   }

   @Override
   public ItemDto createSubItem(String superItemId, String codeType, String code, String name) {
      return ItemDtoFactory.createDto(itemCreationService.createSubItem(Code.fromString(superItemId), createItemCode(codeType, code), name));
   }

   @Override
   public List<ItemHierarchyDto> getAll() {
      return itemRepository.getAll().stream().map(ItemHierarchyDtoFactory::createDto).collect(Collectors.toList());
   }

   @Override
   public List<ItemDto> getGeneralItems() {
      return itemRepository.getGeneralItems().stream().map(ItemDtoFactory::createDto).collect(Collectors.toList());
   }

   @Override
   public ItemDto get(String id) {
      return ItemDtoFactory.createDto(itemRepository.get(Code.fromString(id)));
   }

   @Override
   public void delete(String id) {
      itemRepository.remove(Code.fromString(id));
   }

   private Code createItemCode(String codeType, String code) {
      CodeType type = CodeType.valueOf(codeType);
      return new Code(type, code);
   }
}
