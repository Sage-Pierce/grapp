package com.wisegas.grapp.domain.service;

import com.wisegas.grapp.domain.entity.GrappUser;

public interface GrappUserRepository extends GenericEntityRepository<GrappUser> {
   GrappUser findByEmail(String email);
}
