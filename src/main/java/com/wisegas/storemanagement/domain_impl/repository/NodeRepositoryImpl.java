package com.wisegas.storemanagement.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.storemanagement.domain.entity.Node;
import com.wisegas.storemanagement.domain.repository.NodeRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class NodeRepositoryImpl extends GenericRepositoryImpl<Node> implements NodeRepository {

}
