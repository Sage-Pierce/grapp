package org.codegas.stores.service_impl.api_impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.common.lang.annotation.ApplicationService;
import org.codegas.common.lang.annotation.Transactional;
import org.codegas.stores.domain.entity.Node;
import org.codegas.stores.domain.repository.NodeRepository;
import org.codegas.stores.domain.value.NodeId;
import org.codegas.stores.service.api.NodeService;
import org.codegas.stores.service.dto.NodeDto;
import org.codegas.stores.service_impl.factory.NodeDtoFactory;

@Named
@Singleton
@Transactional
@ApplicationService
public class NodeServiceImpl implements NodeService {

    private final NodeRepository nodeRepository;

    @Inject
    public NodeServiceImpl(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public NodeDto get(String id) {
        return NodeDtoFactory.createDto(nodeRepository.get(NodeId.fromString(id)));
    }

    @Override
    public NodeDto update(String id, String name) {
        Node node = nodeRepository.get(NodeId.fromString(id));
        node.setName(name);
        return NodeDtoFactory.createDto(node);
    }

    @Override
    public void delete(String id) {
        nodeRepository.remove(NodeId.fromString(id));
    }
}
