package com.wisegas.shoppinglist.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.shoppinglist.domain.repository.ShoppingListItemRepository;
import com.wisegas.shoppinglist.domain.value.ShoppingListItemId;
import com.wisegas.shoppinglist.service.api.ShoppingListItemService;
import com.wisegas.shoppinglist.service.dto.ShoppingListItemDto;
import com.wisegas.shoppinglist.service_impl.factory.ShoppingListItemDtoFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@Transactional
@ApplicationService
public class ShoppingListItemServiceImpl implements ShoppingListItemService {

   private final ShoppingListItemRepository shoppingListItemRepository;

   @Inject
   public ShoppingListItemServiceImpl(ShoppingListItemRepository shoppingListItemRepository) {
      this.shoppingListItemRepository = shoppingListItemRepository;
   }

   @Override
   public ShoppingListItemDto get(String id) {
      return ShoppingListItemDtoFactory.createDto(shoppingListItemRepository.get(ShoppingListItemId.fromString(id)));
   }

   @Override
   public void delete(String id) {
      shoppingListItemRepository.remove(ShoppingListItemId.fromString(id));
   }
}
