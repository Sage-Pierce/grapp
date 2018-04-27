package org.codegas.stores.domain_impl.repository;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.service.jpa.RepositoryImpl;
import org.codegas.stores.domain.entity.StoreManager;
import org.codegas.stores.domain.repository.StoreManagerRepository;

@Named
@Singleton
public class StoreManagerRepositoryImpl extends RepositoryImpl<StoreManager> implements StoreManagerRepository {

}
