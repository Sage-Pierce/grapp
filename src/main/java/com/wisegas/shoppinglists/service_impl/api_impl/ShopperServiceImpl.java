package com.wisegas.shoppinglists.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.common.lang.value.Email;
import com.wisegas.shoppinglists.domain.entity.Shopper;
import com.wisegas.shoppinglists.domain.repository.ShopperRepository;
import com.wisegas.shoppinglists.service.api.ShopperService;
import com.wisegas.shoppinglists.service.dto.ShopperDto;
import com.wisegas.shoppinglists.service.dto.ShoppingListDto;
import com.wisegas.shoppinglists.service_impl.factory.ShopperDtoFactory;
import com.wisegas.shoppinglists.service_impl.factory.ShoppingListDtoFactory;

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
   public ShopperDto loadByEmail(Email email) {
      return ShopperDtoFactory.createDto(shopperRepository.findByEmail(email).orElseGet(() -> persistShopperWithEmail(email)));
   }

   @Override
   public ShoppingListDto addList(Email email, String name) {
      return ShoppingListDtoFactory.createDto(shopperRepository.get(email).addList(name));
   }

   private Shopper persistShopperWithEmail(Email email) {
      return shopperRepository.add(new Shopper(email));
   }
}
