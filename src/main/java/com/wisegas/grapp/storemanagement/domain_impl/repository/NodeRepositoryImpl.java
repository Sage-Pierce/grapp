package com.wisegas.grapp.storemanagement.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.grapp.storemanagement.domain.entity.Node;
import com.wisegas.grapp.storemanagement.domain.repository.NodeRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class NodeRepositoryImpl extends GenericRepositoryImpl<Node> implements NodeRepository {

}
