package com.wisegas.grapp.service_impl.api;

import com.wisegas.grapp.domain.entity.GrappUser;
import com.wisegas.grapp.domain.repository.GrappUserRepository;
import com.wisegas.grapp.domain.value.GrappUserID;
import com.wisegas.grapp.service.api.GrappUserService;
import com.wisegas.grapp.service.dto.GrappUserDTO;
import com.wisegas.grapp.service_impl.factory.GrappUserDTOFactory;
import com.wisegas.common.lang.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@Transactional
public class GrappUserServiceImpl implements GrappUserService {

   private final GrappUserRepository grappUserRepository;

   @Inject
   public GrappUserServiceImpl(GrappUserRepository grappUserRepository) {
      this.grappUserRepository = grappUserRepository;
   }

   @Override
   public GrappUserDTO findByID(String id) {
      return GrappUserDTOFactory.createDTO(grappUserRepository.findByID(GrappUserID.fromString(id)));
   }

   @Override
   public GrappUserDTO updateName(String id, String name) {
      GrappUser grappUser = grappUserRepository.findByID(GrappUserID.fromString(id));
      grappUser.setName(name);
      return GrappUserDTOFactory.createDTO(grappUser);
   }
}
