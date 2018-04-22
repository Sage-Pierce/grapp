package org.codegas.shoppinglists.domain.repository;

import java.util.Optional;

import org.codegas.commons.lang.value.Email;
import org.codegas.commons.domain.entity.Repository;
import org.codegas.shoppinglists.domain.entity.Shopper;

public interface ShopperRepository extends Repository<Shopper> {

    Optional<Shopper> findByEmail(Email email);
}
