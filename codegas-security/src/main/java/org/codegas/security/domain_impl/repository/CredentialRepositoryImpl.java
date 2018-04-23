package org.codegas.security.domain_impl.repository;

import java.util.Optional;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.service.jpa.RepositoryImpl;
import org.codegas.security.domain.entity.Credential;
import org.codegas.security.domain.repository.CredentialRepository;

@Named
@Singleton
public class CredentialRepositoryImpl extends RepositoryImpl<Credential> implements CredentialRepository {

    @Override
    public Optional<Credential> findByAccessToken(String accessToken) {
        return entityManager.createQuery("" +
            " SELECT credential" +
            " FROM Credential credential" +
            " WHERE credential.accessToken = :accessToken")
            .setParameter("accessToken", accessToken)
            .getResultStream()
            .findFirst();
    }
}
