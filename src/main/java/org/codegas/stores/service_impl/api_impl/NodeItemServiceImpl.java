package org.codegas.stores.service_impl.api_impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.common.lang.annotation.ApplicationService;
import org.codegas.common.lang.annotation.Transactional;
import org.codegas.stores.domain.repository.NodeItemRepository;
import org.codegas.stores.domain.value.NodeItemId;
import org.codegas.stores.service.api.NodeItemService;
import org.codegas.stores.service.dto.NodeItemDto;
import org.codegas.stores.service_impl.factory.NodeItemDtoFactory;

@Named
@Singleton
@Transactional
@ApplicationService
public class NodeItemServiceImpl implements NodeItemService {

    private final NodeItemRepository nodeItemRepository;

    @Inject
    public NodeItemServiceImpl(NodeItemRepository nodeItemRepository) {
        this.nodeItemRepository = nodeItemRepository;
    }

    @Override
    public NodeItemDto get(String id) {
        return NodeItemDtoFactory.createDto(nodeItemRepository.get(NodeItemId.fromString(id)));
    }

    @Override
    public void delete(String id) {
        nodeItemRepository.remove(NodeItemId.fromString(id));
    }
}
