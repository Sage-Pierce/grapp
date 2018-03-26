package org.codegas.shoppinglists.domain_impl.repository;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.persistence.jpa.GenericRepositoryImpl;
import org.codegas.shoppinglists.domain.entity.ShoppingList;
import org.codegas.shoppinglists.domain.repository.ShoppingListRepository;

@Named
@Singleton
public class ShoppingListRepositoryImpl extends GenericRepositoryImpl<ShoppingList> implements ShoppingListRepository {

}
