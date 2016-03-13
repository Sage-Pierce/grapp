package com.wisegas.grapp.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.grapp.domain.entity.GrappStoreNode;
import com.wisegas.grapp.domain.repository.GrappStoreNodeRepository;
import com.wisegas.grapp.domain.value.GrappStoreNodeID;
import com.wisegas.grapp.service.api.GrappStoreNodeService;
import com.wisegas.grapp.service.dto.GrappStoreNodeDTO;
import com.wisegas.grapp.service_impl.factory.GrappStoreNodeDTOFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@Transactional
@ApplicationService
public class GrappStoreNodeServiceImpl implements GrappStoreNodeService {

   private final GrappStoreNodeRepository grappStoreNodeRepository;

   @Inject
   public GrappStoreNodeServiceImpl(GrappStoreNodeRepository grappStoreNodeRepository) {
      this.grappStoreNodeRepository = grappStoreNodeRepository;
   }

   @Override
   public GrappStoreNodeDTO findByID(String id) {
      return GrappStoreNodeDTOFactory.createDTO(grappStoreNodeRepository.findByID(GrappStoreNodeID.fromString(id)));
   }

   @Override
   public GrappStoreNodeDTO updateByID(String id, String name) {
      GrappStoreNode grappStoreNode = grappStoreNodeRepository.findByID(GrappStoreNodeID.fromString(id));
      grappStoreNode.setName(name);
      return GrappStoreNodeDTOFactory.createDTO(grappStoreNode);
   }
}
