package org.codegas.shoppinglists.service_impl.api_impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.lang.annotation.ApplicationService;
import org.codegas.commons.lang.annotation.Transactional;
import org.codegas.commons.lang.value.CodeName;
import org.codegas.shoppinglists.domain.repository.ShoppingListRepository;
import org.codegas.shoppinglists.domain.value.Item;
import org.codegas.shoppinglists.domain.value.ShoppingListId;
import org.codegas.shoppinglists.service.api.ShoppingListService;
import org.codegas.shoppinglists.service.dto.ShoppingListDto;
import org.codegas.shoppinglists.service.dto.ShoppingListItemDto;
import org.codegas.shoppinglists.service_impl.factory.ShoppingListDtoFactory;
import org.codegas.shoppinglists.service_impl.factory.ShoppingListItemDtoFactory;

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
