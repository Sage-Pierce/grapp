package com.wisegas.stores.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.stores.domain.entity.StoreLayout;
import com.wisegas.stores.domain.repository.StoreLayoutRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class StoreLayoutRepositoryImpl extends GenericRepositoryImpl<StoreLayout> implements StoreLayoutRepository {

}
