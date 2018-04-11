package org.codegas.security.domain_impl.repository;

import java.util.Optional;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.persistence.jpa.GenericRepositoryImpl;
import org.codegas.security.domain.entity.Credential;
import org.codegas.security.domain.entity.User;
import org.codegas.security.domain.repository.UserRepository;

@Named
@Singleton
public class UserRepositoryImpl extends GenericRepositoryImpl<User> implements UserRepository {

    @Override
    public Optional<User> findByCredential(Credential credential) {
        return entityManager.createQuery("" +
            " SELECT userCredential.user" +
            " FROM UserCredential userCredential" +
            " WHERE userCredential.credential = :credential")
            .setParameter("credential", credential)
            .getResultStream().findFirst();
    }
}
