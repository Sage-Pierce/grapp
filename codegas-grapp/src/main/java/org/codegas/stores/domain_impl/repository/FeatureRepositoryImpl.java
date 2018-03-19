package org.codegas.stores.domain_impl.repository;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.persistence.jpa.GenericRepositoryImpl;
import org.codegas.stores.domain.entity.Feature;
import org.codegas.stores.domain.repository.FeatureRepository;

@Named
@Singleton
public class FeatureRepositoryImpl extends GenericRepositoryImpl<Feature> implements FeatureRepository {

}
