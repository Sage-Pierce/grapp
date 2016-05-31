package com.wisegas.stores.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.stores.domain.entity.Store;
import com.wisegas.stores.domain.repository.StoreRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class StoreRepositoryImpl extends GenericRepositoryImpl<Store> implements StoreRepository {

}
