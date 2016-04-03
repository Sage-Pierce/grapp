package com.wisegas.grapp.domain.service;

import com.wisegas.grapp.domain.entity.GrappItem;
import com.wisegas.grapp.domain.value.GrappItemId;

public interface GrappItemUpdateService {
   GrappItem updateName(GrappItemId id, String name);
}
