package org.codegas.security.domain.repository;

import java.util.Optional;

import org.codegas.persistence.api.GenericRepository;
import org.codegas.security.domain.entity.Credential;
import org.codegas.security.domain.entity.User;

public interface UserRepository extends GenericRepository<User> {

    Optional<User> findByCredential(Credential credential);
}
