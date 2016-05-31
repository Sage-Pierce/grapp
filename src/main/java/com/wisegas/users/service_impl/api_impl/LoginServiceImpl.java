package com.wisegas.users.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.common.lang.value.Email;
import com.wisegas.users.domain.entity.User;
import com.wisegas.users.domain.repository.UserRepository;
import com.wisegas.users.service.api.LoginService;
import com.wisegas.users.service.dto.UserDto;
import com.wisegas.users.service_impl.factory.UserDtoFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@Transactional
@ApplicationService
public class LoginServiceImpl implements LoginService {

   private final UserRepository userRepository;

   @Inject
   public LoginServiceImpl(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Override
   public UserDto logIn(Email email, String avatar) {
      User user = getUserByEmail(email);
      user.setAvatar(avatar == null ? user.getAvatar() : avatar);
      return UserDtoFactory.createDto(user);
   }

   private User getUserByEmail(Email email) {
      return userRepository.findByEmail(email).orElseGet(() -> persistUserWithEmail(email));
   }

   private User persistUserWithEmail(Email email) {
      return userRepository.add(new User(email, email.toString(), null));
   }
}
