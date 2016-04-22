package com.wisegas.storemanagement.service.api;

import com.wisegas.storemanagement.service.dto.FeatureDto;

public interface FeatureService {
   FeatureDto get(String id);

   void delete(String id);
}
