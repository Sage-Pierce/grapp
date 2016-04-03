package com.wisegas.grapp.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.grapp.domain.entity.GrappUser;
import com.wisegas.grapp.domain.repository.GrappUserRepository;
import com.wisegas.grapp.domain.value.GrappUserIDFUCK;
import com.wisegas.grapp.service.api.GrappUserService;
import com.wisegas.grapp.service.dto.GrappUserDTO;
import com.wisegas.grapp.service_impl.factory.GrappUserDTOFactory;

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
      return GrappUserDTOFactory.createDTO(grappUserRepository.get(GrappUserIDFUCK.fromString(id)));
   }

   @Override
   public GrappUserDTO update(String id, String name) {
      GrappUser grappUser = grappUserRepository.get(GrappUserIDFUCK.fromString(id));
      grappUser.setName(name);
      return GrappUserDTOFactory.createDTO(grappUser);
   }
}
