package com.wisegas.grapp.domain.service;

import com.wisegas.grapp.domain.entity.GrappItem;
import com.wisegas.grapp.domain.value.GrappItemIDFUCK;

public interface GrappItemCreationService {
   GrappItem createGeneralItem(String name);

   GrappItem createSubItem(GrappItemIDFUCK superItemId, String name);
}
