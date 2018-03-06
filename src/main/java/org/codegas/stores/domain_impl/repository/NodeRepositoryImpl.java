package org.codegas.stores.domain_impl.repository;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.persistence.jpa.impl.GenericRepositoryImpl;
import org.codegas.stores.domain.entity.Node;
import org.codegas.stores.domain.repository.NodeRepository;

@Named
@Singleton
public class NodeRepositoryImpl extends GenericRepositoryImpl<Node> implements NodeRepository {

}
