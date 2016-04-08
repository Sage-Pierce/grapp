package com.wisegas.grapp.storemanagement.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreLayout;
import com.wisegas.grapp.storemanagement.domain.repository.GrappStoreLayoutRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class GrappStoreLayoutRepositoryImpl extends GenericRepositoryImpl<GrappStoreLayout> implements GrappStoreLayoutRepository {

}
