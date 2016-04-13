package com.wisegas.grapp.storemanagement.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNodeItem;
import com.wisegas.grapp.storemanagement.domain.repository.GrappStoreNodeItemRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class GrappStoreNodeItemRepositoryImpl extends GenericRepositoryImpl<GrappStoreNodeItem> implements GrappStoreNodeItemRepository {

}
