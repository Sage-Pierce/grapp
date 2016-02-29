package com.wisegas.grapp.service_impl.factory;

import com.wisegas.grapp.domain.entity.GrappStoreNode;
import com.wisegas.grapp.service.dto.GrappStoreNodeDTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class GrappStoreNodeDTOFactory {

   public static List<GrappStoreNodeDTO> createDTOs(Collection<GrappStoreNode> grappStoreNodes) {
      return grappStoreNodes.stream().map(GrappStoreNodeDTOFactory::createDTO).collect(Collectors.toList());
   }

   public static GrappStoreNodeDTO createDTO(GrappStoreNode grappStoreNode) {
      GrappStoreNodeDTO grappStoreNodeDTO = new GrappStoreNodeDTO();
      grappStoreNodeDTO.setId(grappStoreNode.getId().toString());
      grappStoreNodeDTO.setName(grappStoreNode.getName());
      grappStoreNodeDTO.setLocation(grappStoreNode.getLocation());
      return grappStoreNodeDTO;
   }

   private GrappStoreNodeDTOFactory() {

   }
}
