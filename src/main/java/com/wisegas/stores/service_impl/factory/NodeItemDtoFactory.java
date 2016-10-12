package com.wisegas.stores.service_impl.factory;

import com.wisegas.stores.domain.entity.NodeItem;
import com.wisegas.stores.service.dto.NodeItemDto;

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
