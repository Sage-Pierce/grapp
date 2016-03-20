package com.wisegas.grapp.domain.repository;

import com.wisegas.common.persistence.jpa.api.GenericRepository;
import com.wisegas.grapp.domain.entity.GrappUser;

import java.util.Optional;

public interface GrappUserRepository extends GenericRepository<GrappUser> {
   Optional<GrappUser> findByEmail(String email);
}
