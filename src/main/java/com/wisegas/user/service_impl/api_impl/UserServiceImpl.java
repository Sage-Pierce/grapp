package com.wisegas.user.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.user.domain.entity.User;
import com.wisegas.user.domain.repository.UserRepository;
import com.wisegas.user.domain.value.Email;
import com.wisegas.user.service.api.UserService;
import com.wisegas.user.service.dto.UserDto;
import com.wisegas.user.service_impl.factory.UserDtoFactory;

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
   public UserDto get(String id) {
      return UserDtoFactory.createDto(userRepository.get(Email.fromString(id)));
   }

   @Override
   public UserDto update(String id, String name) {
      User user = userRepository.get(Email.fromString(id));
      user.setName(name);
      return UserDtoFactory.createDto(user);
   }
}
