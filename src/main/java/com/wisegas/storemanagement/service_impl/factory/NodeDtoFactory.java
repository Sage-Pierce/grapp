package com.wisegas.storemanagement.service_impl.factory;

import com.wisegas.storemanagement.domain.entity.Node;
import com.wisegas.storemanagement.service.dto.NodeDto;

import java.util.stream.Collectors;

public final class NodeDtoFactory {

   public static NodeDto createDto(Node node) {
      NodeDto nodeDto = new NodeDto();
      nodeDto.setId(node.getId().toString());
      nodeDto.setName(node.getName());
      nodeDto.setType(node.getType().name());
      nodeDto.setLocation(node.getLocation());
      nodeDto.setItems(node.getItems().stream().map(NodeItemDtoFactory::createDto).collect(Collectors.toList()));
      return nodeDto;
   }

   private NodeDtoFactory() {

   }
}
