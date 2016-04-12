package com.wisegas.grapp.usermanagement.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.grapp.usermanagement.domain.entity.GrappUser;
import com.wisegas.grapp.usermanagement.domain.repository.GrappUserRepository;
import com.wisegas.grapp.usermanagement.domain.value.GrappUserEmail;
import com.wisegas.grapp.usermanagement.service.api.GrappUserService;
import com.wisegas.grapp.usermanagement.service.dto.GrappUserDTO;
import com.wisegas.grapp.usermanagement.service_impl.factory.GrappUserDTOFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@Transactional
@ApplicationService
public class GrappUserServiceImpl implements GrappUserService {

   private final GrappUserRepository grappUserRepository;

   @Inject
   public GrappUserServiceImpl(GrappUserRepository grappUserRepository) {
      this.grappUserRepository = grappUserRepository;
   }

   @Override
   public GrappUserDTO get(String id) {
      return GrappUserDTOFactory.createDTO(grappUserRepository.get(GrappUserEmail.fromString(id)));
   }

   @Override
   public GrappUserDTO update(String id, String name) {
      GrappUser grappUser = grappUserRepository.get(GrappUserEmail.fromString(id));
      grappUser.setName(name);
      return GrappUserDTOFactory.createDTO(grappUser);
   }
}
