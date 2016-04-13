package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.NodeItem;
import com.wisegas.grapp.storemanagement.service.dto.NodeItemDto;

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
