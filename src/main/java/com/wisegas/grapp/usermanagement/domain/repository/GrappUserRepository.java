package com.wisegas.grapp.usermanagement.domain.repository;

import com.wisegas.common.persistence.jpa.api.GenericRepository;
import com.wisegas.grapp.usermanagement.domain.entity.GrappUser;

import java.util.Optional;

public interface GrappUserRepository extends GenericRepository<GrappUser> {
   Optional<GrappUser> findByEmail(String email);
}
