package org.codegas.security.service_impl.factory;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.codegas.security.domain.entity.User;
import org.codegas.security.service.dto.UserDto;

public final class UserDtoFactory {

    private UserDtoFactory() {

    }

    public static UserDto createDto(User user) {
        return createDto(user, UserDto::new);
    }

    public static <T extends UserDto> T createDto(User user, Supplier<T> dtoSupplier) {
        T userDto = dtoSupplier.get();
        userDto.setId(user.getId().toString());
        userDto.setAttributes(user.getAttributes().entrySet().stream()
            .collect(Collectors.toMap(entry -> entry.getKey().name(), Map.Entry::getValue)));
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
