package org.codegas.shoppinglists.service_impl.api;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.lang.annotation.ApplicationService;
import org.codegas.commons.lang.annotation.Transactional;
import org.codegas.commons.lang.value.PrincipalName;
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
    public ShopperDto load(PrincipalName name) {
        return shopperRepository.find(name)
            .map(ShopperDtoFactory::createDto)
            .orElseGet(() -> ShopperDtoFactory.createDto(shopperRepository.add(new Shopper(name))));
    }

    @Override
    public ShoppingListDto addList(PrincipalName shopperName, String listName) {
        return ShoppingListDtoFactory.createDto(shopperRepository.get(shopperName).addList(listName));
    }
}
