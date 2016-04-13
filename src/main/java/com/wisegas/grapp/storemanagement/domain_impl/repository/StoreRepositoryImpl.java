package com.wisegas.grapp.storemanagement.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.grapp.storemanagement.domain.entity.Store;
import com.wisegas.grapp.storemanagement.domain.repository.StoreRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class StoreRepositoryImpl extends GenericRepositoryImpl<Store> implements StoreRepository {

}
