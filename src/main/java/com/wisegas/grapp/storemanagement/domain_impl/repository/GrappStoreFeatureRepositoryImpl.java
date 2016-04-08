package com.wisegas.grapp.storemanagement.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreFeature;
import com.wisegas.grapp.storemanagement.domain.repository.GrappStoreFeatureRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class GrappStoreFeatureRepositoryImpl extends GenericRepositoryImpl<GrappStoreFeature> implements GrappStoreFeatureRepository {

}
