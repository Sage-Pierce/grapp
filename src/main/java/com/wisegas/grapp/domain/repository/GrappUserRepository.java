package com.wisegas.grapp.domain.repository;

import com.wisegas.grapp.domain.entity.GrappUser;
import com.wisegas.common.persistence.jpa.api.GenericRepository;

public interface GrappUserRepository extends GenericRepository<GrappUser> {
   GrappUser findByEmail(String email);
}
