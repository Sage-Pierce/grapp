package com.wisegas.grapp.usermanagement.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.grapp.usermanagement.domain.entity.GrappUser;
import com.wisegas.grapp.usermanagement.domain.repository.GrappUserRepository;
import com.wisegas.grapp.usermanagement.service.api.GrappLoginService;
import com.wisegas.grapp.usermanagement.service.dto.GrappUserDto;
import com.wisegas.grapp.usermanagement.service_impl.factory.GrappUserDtoFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;

@Named
@Singleton
@Transactional
@ApplicationService
public class GrappLoginServiceImpl implements GrappLoginService {

   private final GrappUserRepository grappUserRepository;

   @Inject
   public GrappLoginServiceImpl(GrappUserRepository grappUserRepository) {
      this.grappUserRepository = grappUserRepository;
   }

   @Override
   public GrappUserDto logIn(String email, String avatar) {
      GrappUser grappUser = getGrappUserForEmail(email);
      grappUser.setAvatar(avatar == null ? grappUser.getAvatar() : avatar);
      return GrappUserDtoFactory.createDTO(grappUser);
   }

   private GrappUser getGrappUserForEmail(String email) {
      Optional<GrappUser> foundGrappUser = grappUserRepository.findByEmail(email);
      return foundGrappUser.isPresent() ? foundGrappUser.get() : persistGrappUserWithEmail(email);
   }

   private GrappUser persistGrappUserWithEmail(String email) {
      return grappUserRepository.add(new GrappUser(email, email, null));
   }
}
