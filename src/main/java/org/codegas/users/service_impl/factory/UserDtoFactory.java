package org.codegas.users.service_impl.factory;

import org.codegas.users.domain.entity.User;
import org.codegas.users.service.dto.UserDto;

public final class UserDtoFactory {

    private UserDtoFactory() {

    }

    public static UserDto createDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail().toString());
        userDto.setName(user.getName());
        userDto.setAvatar(user.getAvatar());
        userDto.setAdmin(user.isAdmin());
        userDto.setManager(user.isManager());
        return userDto;
    }
}
