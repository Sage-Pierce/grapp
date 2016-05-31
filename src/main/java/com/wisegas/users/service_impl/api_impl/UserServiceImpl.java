package com.wisegas.users.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.common.lang.value.Email;
import com.wisegas.users.domain.entity.User;
import com.wisegas.users.domain.repository.UserRepository;
import com.wisegas.users.service.api.UserService;
import com.wisegas.users.service.dto.UserDto;
import com.wisegas.users.service_impl.factory.UserDtoFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@Transactional
@ApplicationService
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;

   @Inject
   public UserServiceImpl(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Override
   public UserDto get(Email email) {
      return UserDtoFactory.createDto(userRepository.get(email));
   }

   @Override
   public UserDto update(Email email, String name) {
      User user = userRepository.get(email);
      user.setName(name);
      return UserDtoFactory.createDto(user);
   }
}
