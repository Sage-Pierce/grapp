package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreLayout;
import com.wisegas.grapp.storemanagement.domain.value.GrappStoreNodeId;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreLayoutUpdateResultDTO;

import java.util.List;
import java.util.stream.Collectors;

public class GrappStoreLayoutUpdateResultDTOFactory {

   public static <T> GrappStoreLayoutUpdateResultDTO<T> createDTO(GrappStoreLayout grappStoreLayout, T targetDTO, List<String> affectedNodeIDs) {
      GrappStoreLayoutUpdateResultDTO<T> resultDTO = new GrappStoreLayoutUpdateResultDTO<>();
      resultDTO.setTarget(targetDTO);
      resultDTO.setAffectedNodes(affectedNodeIDs.stream().map(GrappStoreNodeId::fromString).map(grappStoreLayout::getNode).map(GrappStoreNodeDTOFactory::createDTO).collect(Collectors.toList()));
      return resultDTO;
   }
}
