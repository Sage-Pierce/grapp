package com.wisegas.grapp.domain_impl.repository;

import com.wisegas.grapp.domain.entity.GrappStoreNode;
import com.wisegas.grapp.domain.repository.GrappStoreNodeRepository;
import com.wisegas.persistence.jpa.impl.GenericRepositoryImpl;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class GrappStoreNodeRepositoryImpl extends GenericRepositoryImpl<GrappStoreNode> implements GrappStoreNodeRepository {

}
