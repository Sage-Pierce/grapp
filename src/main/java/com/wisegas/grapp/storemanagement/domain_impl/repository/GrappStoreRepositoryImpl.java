package com.wisegas.grapp.storemanagement.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.grapp.storemanagement.domain.entity.GrappStore;
import com.wisegas.grapp.storemanagement.domain.repository.GrappStoreRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class GrappStoreRepositoryImpl extends GenericRepositoryImpl<GrappStore> implements GrappStoreRepository {

}
