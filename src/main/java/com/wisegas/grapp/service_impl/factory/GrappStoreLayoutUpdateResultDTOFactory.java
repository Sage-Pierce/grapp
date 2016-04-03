package com.wisegas.grapp.service_impl.factory;

import com.wisegas.grapp.domain.entity.GrappStoreLayout;
import com.wisegas.grapp.domain.value.GrappStoreNodeIDFUCK;
import com.wisegas.grapp.service.dto.GrappStoreLayoutUpdateResultDTO;

import java.util.List;
import java.util.stream.Collectors;

public class GrappStoreLayoutUpdateResultDTOFactory {

   public static <T> GrappStoreLayoutUpdateResultDTO<T> createDTO(GrappStoreLayout grappStoreLayout, T targetDTO, List<String> affectedNodeIDs) {
      GrappStoreLayoutUpdateResultDTO<T> resultDTO = new GrappStoreLayoutUpdateResultDTO<>();
      resultDTO.setTarget(targetDTO);
      resultDTO.setAffectedNodes(affectedNodeIDs.stream().map(GrappStoreNodeIDFUCK::fromString).map(grappStoreLayout::getNode).map(GrappStoreNodeDTOFactory::createDTO).collect(Collectors.toList()));
      return resultDTO;
   }
}
