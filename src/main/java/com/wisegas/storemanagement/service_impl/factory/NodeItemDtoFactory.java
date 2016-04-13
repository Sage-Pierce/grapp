package com.wisegas.storemanagement.service_impl.factory;

import com.wisegas.storemanagement.domain.entity.NodeItem;
import com.wisegas.storemanagement.service.dto.NodeItemDto;

public final class NodeItemDtoFactory {

   public static NodeItemDto createDto(NodeItem nodeItem) {
      NodeItemDto nodeItemDto = new NodeItemDto();
      nodeItemDto.setId(nodeItem.getId().toString());
      nodeItemDto.setItem(nodeItem.getItem().toCodeName());
      return nodeItemDto;
   }

   private NodeItemDtoFactory() {

   }
}
