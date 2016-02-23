package com.wisegas.grapp.domain.repository;

import com.wisegas.grapp.domain.entity.GrappUser;

public interface GrappUserRepository extends GenericEntityRepository<GrappUser> {
   GrappUser findByEmail(String email);
}
