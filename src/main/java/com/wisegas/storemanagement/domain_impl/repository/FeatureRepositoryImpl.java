package com.wisegas.storemanagement.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.storemanagement.domain.entity.Feature;
import com.wisegas.storemanagement.domain.repository.FeatureRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class FeatureRepositoryImpl extends GenericRepositoryImpl<Feature> implements FeatureRepository {

}