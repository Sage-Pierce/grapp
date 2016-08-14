package com.wisegas.shoppinglists.domain.repository;

import com.wisegas.common.persistence.api.GenericRepository;
import com.wisegas.common.lang.value.Email;
import com.wisegas.shoppinglists.domain.entity.Shopper;

import java.util.Optional;

public interface ShopperRepository extends GenericRepository<Shopper> {
   Optional<Shopper> findByEmail(Email email);
}
