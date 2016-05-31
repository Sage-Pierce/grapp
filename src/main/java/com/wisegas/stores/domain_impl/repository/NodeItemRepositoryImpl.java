package com.wisegas.stores.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.stores.domain.entity.NodeItem;
import com.wisegas.stores.domain.repository.NodeItemRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class NodeItemRepositoryImpl extends GenericRepositoryImpl<NodeItem> implements NodeItemRepository {

}
