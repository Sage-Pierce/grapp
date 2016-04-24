package com.wisegas.shoppinglist.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.shoppinglist.domain.entity.ShoppingList;
import com.wisegas.shoppinglist.domain.repository.ShoppingListRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class ShoppingListRepositoryImpl extends GenericRepositoryImpl<ShoppingList> implements ShoppingListRepository {

}
