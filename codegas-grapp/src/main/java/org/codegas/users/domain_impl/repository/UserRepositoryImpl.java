package org.codegas.users.domain_impl.repository;

import java.util.Optional;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.lang.value.Email;
import org.codegas.commons.persistence.jpa.repository.GenericRepositoryImpl;
import org.codegas.users.domain.entity.User;
import org.codegas.users.domain.repository.UserRepository;

@Named
@Singleton
public class UserRepositoryImpl extends GenericRepositoryImpl<User> implements UserRepository {

    @Override
    public Optional<User> findByEmail(Email email) {
        try {
            return Optional.of(entityManager.createQuery(" SELECT user " +
                    " FROM User user " +
                    " WHERE user.email = :email",
                User.class)
                .setParameter("email", email.toString())
                .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
