package org.codegas.users.service_impl.api_impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.lang.annotation.ApplicationService;
import org.codegas.commons.lang.annotation.Transactional;
import org.codegas.commons.lang.value.Email;
import org.codegas.users.domain.entity.User;
import org.codegas.users.domain.repository.UserRepository;
import org.codegas.users.service.api.LoginService;
import org.codegas.users.service.dto.UserDto;
import org.codegas.users.service_impl.factory.UserDtoFactory;

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
