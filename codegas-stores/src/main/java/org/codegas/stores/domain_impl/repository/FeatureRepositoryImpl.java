package org.codegas.stores.domain_impl.repository;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.jpa.domain.persistence.RepositoryImpl;
import org.codegas.stores.domain.entity.Feature;
import org.codegas.stores.domain.repository.FeatureRepository;

@Named
@Singleton
public class FeatureRepositoryImpl extends RepositoryImpl<Feature> implements FeatureRepository {

}
