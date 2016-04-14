package com.wisegas.storemanagement.service_impl.factory;

import com.wisegas.storemanagement.domain.entity.Layout;
import com.wisegas.storemanagement.domain.value.NodeId;
import com.wisegas.storemanagement.service.dto.LayoutUpdateDto;

import java.util.List;
import java.util.stream.Collectors;

public final class LayoutUpdateDtoFactory {

   public static <T> LayoutUpdateDto<T> createDto(Layout layout, T targetDto, List<String> affectedNodeIds) {
      LayoutUpdateDto<T> resultDto = new LayoutUpdateDto<>();
      resultDto.setTarget(targetDto);
      resultDto.setAffectedNodes(affectedNodeIds.stream().map(NodeId::fromString).map(layout::getNode).map(NodeDtoFactory::createDto).collect(Collectors.toList()));
      return resultDto;
   }

   private LayoutUpdateDtoFactory() {

   }
}
