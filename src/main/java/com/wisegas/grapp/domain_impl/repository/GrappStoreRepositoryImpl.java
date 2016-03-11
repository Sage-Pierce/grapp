package com.wisegas.grapp.domain_impl.repository;

import com.wisegas.grapp.domain.entity.GrappStore;
import com.wisegas.grapp.domain.repository.GrappStoreRepository;
import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class GrappStoreRepositoryImpl extends GenericRepositoryImpl<GrappStore> implements GrappStoreRepository {

}
