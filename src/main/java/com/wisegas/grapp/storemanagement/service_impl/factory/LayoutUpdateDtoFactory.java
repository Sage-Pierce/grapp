package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.Layout;
import com.wisegas.grapp.storemanagement.domain.value.NodeId;
import com.wisegas.grapp.storemanagement.service.dto.LayoutUpdateDto;

import java.util.List;
import java.util.stream.Collectors;

public final class LayoutUpdateDtoFactory {

   public static <T> LayoutUpdateDto<T> createDto(Layout layout, T targetDto, List<String> affectedNodeIDs) {
      LayoutUpdateDto<T> resultDto = new LayoutUpdateDto<>();
      resultDto.setTarget(targetDto);
      resultDto.setAffectedNodes(affectedNodeIDs.stream().map(NodeId::fromString).map(layout::getNode).map(NodeDtoFactory::createDto).collect(Collectors.toList()));
      return resultDto;
   }

   private LayoutUpdateDtoFactory() {

   }
}
