package com.wisegas.shoppinglists.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.shoppinglists.domain.entity.ShoppingListItem;
import com.wisegas.shoppinglists.domain.repository.ShoppingListItemRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class ShoppingListItemRepositoryImpl extends GenericRepositoryImpl<ShoppingListItem> implements ShoppingListItemRepository {

}
