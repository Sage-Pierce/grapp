package com.wisegas.user.domain.repository;

import com.wisegas.common.lang.entity.GenericRepository;
import com.wisegas.common.lang.value.Email;
import com.wisegas.user.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User> {
   Optional<User> findByEmail(Email email);
}
