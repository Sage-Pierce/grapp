package com.wisegas.grapp.service_impl.api;

import com.wisegas.grapp.domain.repository.GrappStoreNodeRepository;
import com.wisegas.grapp.domain.value.GrappStoreNodeID;
import com.wisegas.grapp.service.api.GrappStoreNodeService;
import com.wisegas.grapp.service.dto.GrappStoreNodeDTO;
import com.wisegas.grapp.service_impl.factory.GrappStoreNodeDTOFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@Transactional
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
}
