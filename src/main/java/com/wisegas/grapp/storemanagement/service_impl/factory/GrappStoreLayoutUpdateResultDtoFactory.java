package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreLayout;
import com.wisegas.grapp.storemanagement.domain.value.GrappStoreNodeId;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreLayoutUpdateResultDTOO;

import java.util.List;
import java.util.stream.Collectors;

public final class GrappStoreLayoutUpdateResultDtoFactory {

   public static <T> GrappStoreLayoutUpdateResultDTOO<T> createDto(GrappStoreLayout grappStoreLayout, T targetDto, List<String> affectedNodeIDs) {
      GrappStoreLayoutUpdateResultDTOO<T> resultDto = new GrappStoreLayoutUpdateResultDTOO<>();
      resultDto.setTarget(targetDto);
      resultDto.setAffectedNodes(affectedNodeIDs.stream().map(GrappStoreNodeId::fromString).map(grappStoreLayout::getNode).map(GrappStoreNodeDtoFactory::createDto).collect(Collectors.toList()));
      return resultDto;
   }

   private GrappStoreLayoutUpdateResultDtoFactory() {

   }
}
