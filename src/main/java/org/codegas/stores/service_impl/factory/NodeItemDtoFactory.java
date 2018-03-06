package org.codegas.stores.service_impl.factory;

import org.codegas.stores.domain.entity.NodeItem;
import org.codegas.stores.service.dto.NodeItemDto;

public final class NodeItemDtoFactory {

    private NodeItemDtoFactory() {

    }

    public static NodeItemDto createDto(NodeItem nodeItem) {
        NodeItemDto nodeItemDto = new NodeItemDto();
        nodeItemDto.setId(nodeItem.getId().toString());
        nodeItemDto.setItem(nodeItem.getItem().toCodeName());
        return nodeItemDto;
    }
}
