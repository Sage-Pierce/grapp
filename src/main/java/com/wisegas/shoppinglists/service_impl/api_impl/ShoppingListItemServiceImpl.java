package com.wisegas.shoppinglists.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.shoppinglists.domain.entity.ShoppingListItem;
import com.wisegas.shoppinglists.domain.repository.ShoppingListItemRepository;
import com.wisegas.shoppinglists.domain.value.ShoppingListItemId;
import com.wisegas.shoppinglists.service.api.ShoppingListItemService;
import com.wisegas.shoppinglists.service.dto.ShoppingListItemDto;
import com.wisegas.shoppinglists.service_impl.factory.ShoppingListItemDtoFactory;

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
   public ShoppingListItemDto update(String id, boolean obtained) {
      ShoppingListItem shoppingListItem = shoppingListItemRepository.get(ShoppingListItemId.fromString(id));
      shoppingListItem.setObtained(obtained);
      return ShoppingListItemDtoFactory.createDto(shoppingListItem);
   }

   @Override
   public void delete(String id) {
      shoppingListItemRepository.remove(ShoppingListItemId.fromString(id));
   }
}
