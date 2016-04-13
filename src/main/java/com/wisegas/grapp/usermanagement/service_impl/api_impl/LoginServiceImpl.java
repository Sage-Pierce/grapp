package com.wisegas.grapp.usermanagement.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.grapp.usermanagement.domain.entity.User;
import com.wisegas.grapp.usermanagement.domain.repository.UserRepository;
import com.wisegas.grapp.usermanagement.service.api.LoginService;
import com.wisegas.grapp.usermanagement.service.dto.UserDto;
import com.wisegas.grapp.usermanagement.service_impl.factory.UserDtoFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;

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
   public UserDto logIn(String email, String avatar) {
      User user = getUserByEmail(email);
      user.setAvatar(avatar == null ? user.getAvatar() : avatar);
      return UserDtoFactory.createDto(user);
   }

   private User getUserByEmail(String email) {
      Optional<User> foundUser = userRepository.findByEmail(email);
      return foundUser.isPresent() ? foundUser.get() : persistUserWithEmail(email);
   }

   private User persistUserWithEmail(String email) {
      return userRepository.add(new User(email, email, null));
   }
}
