package com.wisegas.storemanagement.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.storemanagement.domain.entity.Layout;
import com.wisegas.storemanagement.domain.repository.LayoutRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class LayoutRepositoryImpl extends GenericRepositoryImpl<Layout> implements LayoutRepository {

}
