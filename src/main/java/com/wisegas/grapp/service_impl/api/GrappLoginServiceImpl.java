package com.wisegas.grapp.service_impl.api;

import com.wisegas.grapp.domain.entity.GrappUser;
import com.wisegas.grapp.domain.repository.GrappUserRepository;
import com.wisegas.grapp.service.api.GrappLoginService;
import com.wisegas.grapp.service.dto.GrappUserDTO;
import com.wisegas.grapp.service_impl.factory.GrappUserDTOFactory;
import com.wisegas.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@Transactional
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
      GrappUser grappUser = grappUserRepository.findByEmail(email);
      if (grappUser == null) {
         grappUser = persistGrappUserWithEmail(email);
      }
      return grappUser;
   }

   private GrappUser persistGrappUserWithEmail(String email) {
      GrappUser grappUser = new GrappUser(email, email, null);
      return grappUserRepository.add(grappUser);
   }
}
