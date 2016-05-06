package com.wisegas.storemanagement.service_impl.factory;

import com.wisegas.storemanagement.domain.entity.StoreLayout;
import com.wisegas.storemanagement.domain.value.NodeId;
import com.wisegas.storemanagement.service.dto.StoreLayoutUpdateDto;

import java.util.List;
import java.util.stream.Collectors;

public final class StoreLayoutUpdateDtoFactory {

   public static <T> StoreLayoutUpdateDto<T> createDto(StoreLayout storeLayout, T targetDto, List<String> affectedNodeIds) {
      StoreLayoutUpdateDto<T> resultDto = new StoreLayoutUpdateDto<>();
      resultDto.setTarget(targetDto);
      resultDto.setAffectedNodes(affectedNodeIds.stream().map(NodeId::fromString).map(storeLayout::getNode).map(NodeDtoFactory::createDto).collect(Collectors.toList()));
      return resultDto;
   }

   private StoreLayoutUpdateDtoFactory() {

   }
}
