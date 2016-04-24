package com.wisegas.shoppinglist.domain.repository;

import com.wisegas.common.lang.entity.GenericRepository;
import com.wisegas.shoppinglist.domain.entity.Shopper;

import java.util.Optional;

public interface ShopperRepository extends GenericRepository<Shopper> {
   Optional<Shopper> findByEmail(String email);
}
