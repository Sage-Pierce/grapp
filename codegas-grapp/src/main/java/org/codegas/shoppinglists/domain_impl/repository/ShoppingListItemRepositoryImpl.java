package org.codegas.shoppinglists.domain_impl.repository;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.persistence.jpa.impl.GenericRepositoryImpl;
import org.codegas.shoppinglists.domain.entity.ShoppingListItem;
import org.codegas.shoppinglists.domain.repository.ShoppingListItemRepository;

@Named
@Singleton
public class ShoppingListItemRepositoryImpl extends GenericRepositoryImpl<ShoppingListItem> implements ShoppingListItemRepository {

}
