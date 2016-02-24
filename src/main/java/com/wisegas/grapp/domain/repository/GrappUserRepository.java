package com.wisegas.grapp.domain.repository;

import com.wisegas.grapp.domain.entity.GrappUser;
import com.wisegas.persistence.jpa.repository_api.GenericRepository;

public interface GrappUserRepository extends GenericRepository<GrappUser> {
   GrappUser findByEmail(String email);
}
