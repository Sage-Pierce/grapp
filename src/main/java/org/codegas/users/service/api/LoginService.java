package org.codegas.users.service.api;

import org.codegas.common.lang.value.Email;
import org.codegas.users.service.dto.UserDto;

public interface LoginService {

    UserDto logIn(Email email, String avatar);
}
