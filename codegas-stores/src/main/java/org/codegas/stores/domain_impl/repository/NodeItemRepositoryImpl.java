package org.codegas.stores.domain_impl.repository;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.persistence.jpa.RepositoryImpl;
import org.codegas.stores.domain.entity.NodeItem;
import org.codegas.stores.domain.repository.NodeItemRepository;

@Named
@Singleton
public class NodeItemRepositoryImpl extends RepositoryImpl<NodeItem> implements NodeItemRepository {

}
