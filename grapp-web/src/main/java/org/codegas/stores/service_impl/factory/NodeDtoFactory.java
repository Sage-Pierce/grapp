package org.codegas.stores.service_impl.factory;

import java.util.stream.Collectors;

import org.codegas.stores.domain.entity.Node;
import org.codegas.stores.service.dto.NodeDto;

public final class NodeDtoFactory {

    private NodeDtoFactory() {

    }

    public static NodeDto createDto(Node node) {
        NodeDto nodeDto = new NodeDto();
        nodeDto.setId(node.getId().toString());
        nodeDto.setName(node.getName());
        nodeDto.setType(node.getType().name());
        nodeDto.setLocation(node.getLocation());
        nodeDto.setItems(node.getItems().stream().map(NodeItemDtoFactory::createDto).collect(Collectors.toList()));
        return nodeDto;
    }
}
