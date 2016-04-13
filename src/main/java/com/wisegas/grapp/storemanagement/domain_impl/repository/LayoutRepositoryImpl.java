package com.wisegas.grapp.storemanagement.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.grapp.storemanagement.domain.entity.Layout;
import com.wisegas.grapp.storemanagement.domain.repository.LayoutRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class LayoutRepositoryImpl extends GenericRepositoryImpl<Layout> implements LayoutRepository {

}
