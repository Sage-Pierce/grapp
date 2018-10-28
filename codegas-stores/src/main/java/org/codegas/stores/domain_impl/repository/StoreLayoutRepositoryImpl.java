package org.codegas.stores.domain_impl.repository;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.jpa.domain.persistence.RepositoryImpl;
import org.codegas.stores.domain.entity.StoreLayout;
import org.codegas.stores.domain.repository.StoreLayoutRepository;

@Named
@Singleton
public class StoreLayoutRepositoryImpl extends RepositoryImpl<StoreLayout> implements StoreLayoutRepository {

}
