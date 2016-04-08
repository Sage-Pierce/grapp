package com.wisegas.grapp.itemmanagement.domain.service;

import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem;
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemId;

public interface GrappItemUpdateService {
   GrappItem updateName(GrappItemId id, String name);
}
