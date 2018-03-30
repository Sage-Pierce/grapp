package org.codegas.security.domain.repository;

import java.util.Optional;

import org.codegas.persistence.repository.GenericRepository;
import org.codegas.security.domain.entity.Credential;

public interface CredentialRepository extends GenericRepository<Credential> {

    Optional<Credential> findByAccessToken(String accessToken);
}
