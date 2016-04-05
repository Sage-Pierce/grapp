package com.wisegas.grapp.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.grapp.domain.entity.GrappUser;
import com.wisegas.grapp.domain.repository.GrappUserRepository;
import com.wisegas.grapp.service.api.GrappLoginService;
import com.wisegas.grapp.service.dto.GrappUserDTO;
import com.wisegas.grapp.service_impl.factory.GrappUserDTOFactory;

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
   public GrappUserDTO logIn(String email, String avatar) {
      GrappUser grappUser = getGrappUserForEmail(email);
      grappUser.setAvatar(avatar == null ? grappUser.getAvatar() : avatar);
      return GrappUserDTOFactory.createDTO(grappUser);
   }

   private GrappUser getGrappUserForEmail(String email) {
      Optional<GrappUser> foundGrappUser = grappUserRepository.findByEmail(email);
      return foundGrappUser.isPresent() ? foundGrappUser.get() : persistGrappUserWithEmail(email);
   }

   private GrappUser persistGrappUserWithEmail(String email) {
      return grappUserRepository.add(new GrappUser(email, email, null));
   }
}
