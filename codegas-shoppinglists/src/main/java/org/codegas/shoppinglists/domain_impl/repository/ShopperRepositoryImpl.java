package org.codegas.shoppinglists.domain_impl.repository;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.jpa.domain.persistence.RepositoryImpl;
import org.codegas.shoppinglists.domain.entity.Shopper;
import org.codegas.shoppinglists.domain.repository.ShopperRepository;

@Named
@Singleton
public class ShopperRepositoryImpl extends RepositoryImpl<Shopper> implements ShopperRepository {

}
