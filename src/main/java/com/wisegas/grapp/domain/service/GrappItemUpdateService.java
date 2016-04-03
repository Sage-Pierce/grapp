package com.wisegas.grapp.domain.service;

import com.wisegas.grapp.domain.entity.GrappItem;
import com.wisegas.grapp.domain.value.GrappItemIDFUCK;

public interface GrappItemUpdateService {
   GrappItem updateName(GrappItemIDFUCK id, String name);
}
