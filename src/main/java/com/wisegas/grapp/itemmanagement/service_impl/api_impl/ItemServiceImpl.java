package com.wisegas.grapp.itemmanagement.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.grapp.itemmanagement.domain.repository.ItemRepository;
import com.wisegas.grapp.itemmanagement.domain.service.ItemCreationService;
import com.wisegas.grapp.itemmanagement.domain.value.Code;
import com.wisegas.grapp.itemmanagement.domain.value.CodeType;
import com.wisegas.grapp.itemmanagement.service.api.ItemService;
import com.wisegas.grapp.itemmanagement.service.dto.ItemDto;
import com.wisegas.grapp.itemmanagement.service_impl.factory.ItemDtoFactory;

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
   public List<ItemDto> getAll() {
      return itemRepository.getAll().stream().map(ItemDtoFactory::createDto).collect(Collectors.toList());
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
      itemRepository.remove(itemRepository.get(Code.fromString(id)));
   }

   private Code createItemCode(String codeType, String code) {
      return new Code(CodeType.valueOf(codeType), code);
   }
}
