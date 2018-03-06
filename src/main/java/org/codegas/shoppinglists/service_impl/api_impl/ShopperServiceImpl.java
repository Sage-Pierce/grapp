package org.codegas.shoppinglists.service_impl.api_impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.common.lang.annotation.ApplicationService;
import org.codegas.common.lang.annotation.Transactional;
import org.codegas.common.lang.value.Email;
import org.codegas.shoppinglists.domain.entity.Shopper;
import org.codegas.shoppinglists.domain.repository.ShopperRepository;
import org.codegas.shoppinglists.service.api.ShopperService;
import org.codegas.shoppinglists.service.dto.ShopperDto;
import org.codegas.shoppinglists.service.dto.ShoppingListDto;
import org.codegas.shoppinglists.service_impl.factory.ShopperDtoFactory;
import org.codegas.shoppinglists.service_impl.factory.ShoppingListDtoFactory;

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
