package org.codegas.users.domain.repository;

import java.util.Optional;

import org.codegas.commons.lang.value.Email;
import org.codegas.commons.persistence.repository.GenericRepository;
import org.codegas.users.domain.entity.User;

public interface UserRepository extends GenericRepository<User> {

    Optional<User> findByEmail(Email email);
}
