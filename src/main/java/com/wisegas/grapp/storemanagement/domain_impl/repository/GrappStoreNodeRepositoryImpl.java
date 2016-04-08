package com.wisegas.grapp.storemanagement.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNode;
import com.wisegas.grapp.storemanagement.domain.repository.GrappStoreNodeRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class GrappStoreNodeRepositoryImpl extends GenericRepositoryImpl<GrappStoreNode> implements GrappStoreNodeRepository {

}
