package com.wisegas.shoppinglists.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.common.lang.value.CodeName;
import com.wisegas.shoppinglists.domain.repository.ShoppingListRepository;
import com.wisegas.shoppinglists.domain.value.Item;
import com.wisegas.shoppinglists.domain.value.ShoppingListId;
import com.wisegas.shoppinglists.service.api.ShoppingListService;
import com.wisegas.shoppinglists.service.dto.ShoppingListDto;
import com.wisegas.shoppinglists.service.dto.ShoppingListItemDto;
import com.wisegas.shoppinglists.service_impl.factory.ShoppingListDtoFactory;
import com.wisegas.shoppinglists.service_impl.factory.ShoppingListItemDtoFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@Transactional
@ApplicationService
public class ShoppingListServiceImpl implements ShoppingListService {

   private final ShoppingListRepository shoppingListRepository;

   @Inject
   public ShoppingListServiceImpl(ShoppingListRepository shoppingListRepository) {
      this.shoppingListRepository = shoppingListRepository;
   }

   @Override
   public ShoppingListDto get(String id) {
      return ShoppingListDtoFactory.createDto(shoppingListRepository.get(ShoppingListId.fromString(id)));
   }

   @Override
   public ShoppingListItemDto addItem(String id, CodeName item) {
      return ShoppingListItemDtoFactory.createDto(shoppingListRepository.get(ShoppingListId.fromString(id)).addItem(new Item(item)));
   }

   @Override
   public void delete(String id) {
      shoppingListRepository.remove(ShoppingListId.fromString(id));
   }
}
