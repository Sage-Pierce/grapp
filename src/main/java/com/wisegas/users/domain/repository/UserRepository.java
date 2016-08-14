package com.wisegas.users.domain.repository;

import com.wisegas.common.persistence.api.GenericRepository;
import com.wisegas.common.lang.value.Email;
import com.wisegas.users.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User> {
   Optional<User> findByEmail(Email email);
}
