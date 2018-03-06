package org.codegas.users.service.api;

import org.codegas.common.lang.value.Email;
import org.codegas.users.service.dto.UserDto;

public interface UserService {

    UserDto get(Email email);

    UserDto update(Email email, String name);
}
