package org.codegas.stores.domain_impl.repository;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.persistence.jpa.repository.GenericRepositoryImpl;
import org.codegas.stores.domain.entity.Store;
import org.codegas.stores.domain.repository.StoreRepository;

@Named
@Singleton
public class StoreRepositoryImpl extends GenericRepositoryImpl<Store> implements StoreRepository {

}
