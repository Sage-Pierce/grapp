package org.codegas.shoppinglists.domain.repository;

import java.util.Optional;

import org.codegas.commons.lang.value.Email;
import org.codegas.commons.persistence.api.GenericRepository;
import org.codegas.shoppinglists.domain.entity.Shopper;

public interface ShopperRepository extends GenericRepository<Shopper> {

    Optional<Shopper> findByEmail(Email email);
}
