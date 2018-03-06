package org.codegas.shoppinglists.service_impl.api_impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.lang.annotation.ApplicationService;
import org.codegas.commons.lang.annotation.Transactional;
import org.codegas.shoppinglists.domain.entity.ShoppingListItem;
import org.codegas.shoppinglists.domain.repository.ShoppingListItemRepository;
import org.codegas.shoppinglists.domain.value.ShoppingListItemId;
import org.codegas.shoppinglists.service.api.ShoppingListItemService;
import org.codegas.shoppinglists.service.dto.ShoppingListItemDto;
import org.codegas.shoppinglists.service_impl.factory.ShoppingListItemDtoFactory;

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
