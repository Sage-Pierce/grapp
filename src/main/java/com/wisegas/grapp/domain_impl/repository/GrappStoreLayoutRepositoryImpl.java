package com.wisegas.grapp.domain_impl.repository;

import com.wisegas.grapp.domain.entity.GrappStoreLayout;
import com.wisegas.grapp.domain.repository.GrappStoreLayoutRepository;
import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class GrappStoreLayoutRepositoryImpl extends GenericRepositoryImpl<GrappStoreLayout> implements GrappStoreLayoutRepository {

}
