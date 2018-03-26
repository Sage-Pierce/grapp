package org.codegas.shoppinglists.domain_impl.repository;

import java.util.Optional;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.lang.value.Email;
import org.codegas.persistence.jpa.GenericRepositoryImpl;
import org.codegas.shoppinglists.domain.entity.Shopper;
import org.codegas.shoppinglists.domain.repository.ShopperRepository;

@Named
@Singleton
public class ShopperRepositoryImpl extends GenericRepositoryImpl<Shopper> implements ShopperRepository {

    @Override
    public Optional<Shopper> findByEmail(Email email) {
        try {
            return Optional.of(entityManager.createQuery(" SELECT shopper " +
                    " FROM Shopper shopper " +
                    " WHERE shopper.email = :email",
                Shopper.class)
                .setParameter("email", email.toString())
                .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
