package org.codegas.shoppinglists.domain_impl.repository;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.service.jpa.RepositoryImpl;
import org.codegas.shoppinglists.domain.entity.ShoppingList;
import org.codegas.shoppinglists.domain.repository.ShoppingListRepository;

@Named
@Singleton
public class ShoppingListRepositoryImpl extends RepositoryImpl<ShoppingList> implements ShoppingListRepository {

}
