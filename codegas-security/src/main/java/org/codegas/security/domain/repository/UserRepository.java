package org.codegas.security.domain.repository;

import java.util.Optional;

import org.codegas.persistence.repository.GenericRepository;
import org.codegas.security.domain.entity.Credential;
import org.codegas.security.domain.entity.User;
import org.codegas.security.domain.value.CredentialId;

public interface UserRepository extends GenericRepository<User> {

    Optional<User> findByCredential(Credential credential);
}
