package org.codegas.shoppinglists.domain_impl.repository;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.persistence.jpa.RepositoryImpl;
import org.codegas.shoppinglists.domain.entity.ShoppingListItem;
import org.codegas.shoppinglists.domain.repository.ShoppingListItemRepository;

@Named
@Singleton
public class ShoppingListItemRepositoryImpl extends RepositoryImpl<ShoppingListItem> implements ShoppingListItemRepository {

}
