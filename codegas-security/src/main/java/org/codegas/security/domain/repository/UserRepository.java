package org.codegas.security.domain.repository;

import java.util.Optional;

import org.codegas.service.api.Repository;
import org.codegas.security.domain.entity.Credential;
import org.codegas.security.domain.entity.User;

public interface UserRepository extends Repository<User> {

    Optional<User> findByCredential(Credential credential);
}
