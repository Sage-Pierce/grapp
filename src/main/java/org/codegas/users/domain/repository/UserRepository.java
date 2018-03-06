package org.codegas.users.domain.repository;

import java.util.Optional;

import org.codegas.common.lang.value.Email;
import org.codegas.common.persistence.api.GenericRepository;
import org.codegas.users.domain.entity.User;

public interface UserRepository extends GenericRepository<User> {

    Optional<User> findByEmail(Email email);
}
