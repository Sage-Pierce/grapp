package org.codegas.security.domain.repository;

import java.util.Optional;

import org.codegas.commons.domain.persistence.Repository;
import org.codegas.security.domain.entity.Credential;

public interface CredentialRepository extends Repository<Credential> {

    Optional<Credential> findByAccessToken(String accessToken);
}
