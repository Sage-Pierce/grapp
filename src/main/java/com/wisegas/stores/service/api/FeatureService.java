package com.wisegas.stores.service.api;

import com.wisegas.stores.service.dto.FeatureDto;

public interface FeatureService {
   FeatureDto get(String id);

   void delete(String id);
}
