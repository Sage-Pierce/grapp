package com.wisegas.shoppinglist.domain.repository;

import com.wisegas.common.lang.entity.GenericRepository;
import com.wisegas.common.lang.value.Email;
import com.wisegas.shoppinglist.domain.entity.Shopper;

import java.util.Optional;

public interface ShopperRepository extends GenericRepository<Shopper> {
   Optional<Shopper> findByEmail(Email email);
}
