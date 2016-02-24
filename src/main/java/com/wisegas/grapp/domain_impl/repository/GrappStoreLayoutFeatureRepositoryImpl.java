package com.wisegas.grapp.domain_impl.repository;

import com.wisegas.grapp.domain.entity.GrappStoreLayoutFeature;
import com.wisegas.grapp.domain.repository.GrappStoreLayoutFeatureRepository;
import com.wisegas.persistence.jpa.impl.GenericRepositoryImpl;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class GrappStoreLayoutFeatureRepositoryImpl extends GenericRepositoryImpl<GrappStoreLayoutFeature> implements GrappStoreLayoutFeatureRepository {

}
