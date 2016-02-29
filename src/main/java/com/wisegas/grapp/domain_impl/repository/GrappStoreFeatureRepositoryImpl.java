package com.wisegas.grapp.domain_impl.repository;

import com.wisegas.grapp.domain.entity.GrappStoreFeature;
import com.wisegas.grapp.domain.repository.GrappStoreFeatureRepository;
import com.wisegas.persistence.jpa.impl.GenericRepositoryImpl;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class GrappStoreFeatureRepositoryImpl extends GenericRepositoryImpl<GrappStoreFeature> implements GrappStoreFeatureRepository {

}
