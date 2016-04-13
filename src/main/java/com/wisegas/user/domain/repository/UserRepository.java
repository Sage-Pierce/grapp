package com.wisegas.user.domain.repository;

import com.wisegas.common.persistence.jpa.api.GenericRepository;
import com.wisegas.user.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User> {
   Optional<User> findByEmail(String email);
}
