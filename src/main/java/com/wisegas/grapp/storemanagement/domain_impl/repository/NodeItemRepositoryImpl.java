package com.wisegas.grapp.storemanagement.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.grapp.storemanagement.domain.entity.NodeItem;
import com.wisegas.grapp.storemanagement.domain.repository.NodeItemRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class NodeItemRepositoryImpl extends GenericRepositoryImpl<NodeItem> implements NodeItemRepository {

}
