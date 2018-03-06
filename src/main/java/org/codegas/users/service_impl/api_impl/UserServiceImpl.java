package org.codegas.users.service_impl.api_impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.lang.annotation.ApplicationService;
import org.codegas.commons.lang.annotation.Transactional;
import org.codegas.commons.lang.value.Email;
import org.codegas.users.domain.entity.User;
import org.codegas.users.domain.repository.UserRepository;
import org.codegas.users.service.api.UserService;
import org.codegas.users.service.dto.UserDto;
import org.codegas.users.service_impl.factory.UserDtoFactory;

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
