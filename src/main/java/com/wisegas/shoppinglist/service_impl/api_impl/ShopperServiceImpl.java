package com.wisegas.shoppinglist.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.common.lang.value.Email;
import com.wisegas.shoppinglist.domain.entity.Shopper;
import com.wisegas.shoppinglist.domain.repository.ShopperRepository;
import com.wisegas.shoppinglist.service.api.ShopperService;
import com.wisegas.shoppinglist.service.dto.ShopperDto;
import com.wisegas.shoppinglist.service.dto.ShoppingListDto;
import com.wisegas.shoppinglist.service_impl.factory.ShopperDtoFactory;
import com.wisegas.shoppinglist.service_impl.factory.ShoppingListDtoFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@Transactional
@ApplicationService
public class ShopperServiceImpl implements ShopperService {

   private final ShopperRepository shopperRepository;

   @Inject
   public ShopperServiceImpl(ShopperRepository shopperRepository) {
      this.shopperRepository = shopperRepository;
   }

   @Override
   public ShopperDto loadByEmail(String emailString) {
      Email email = Email.fromString(emailString);
      return ShopperDtoFactory.createDto(shopperRepository.findByEmail(email).orElseGet(() -> persistShopperWithEmail(email)));
   }

   @Override
   public ShoppingListDto addList(String id, String name) {
      return ShoppingListDtoFactory.createDto(shopperRepository.get(Email.fromString(id)).addList(name));
   }

   private Shopper persistShopperWithEmail(Email email) {
      return shopperRepository.add(new Shopper(email));
   }
}
