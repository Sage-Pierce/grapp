package org.codegas.stores.domain_impl.repository;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.persistence.jpa.impl.GenericRepositoryImpl;
import org.codegas.stores.domain.entity.StoreLayout;
import org.codegas.stores.domain.repository.StoreLayoutRepository;

@Named
@Singleton
public class StoreLayoutRepositoryImpl extends GenericRepositoryImpl<StoreLayout> implements StoreLayoutRepository {

}
