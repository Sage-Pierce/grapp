package org.codegas.stores.service.api;

import org.codegas.stores.service.dto.FeatureDto;

public interface FeatureService {

    FeatureDto get(String id);

    void delete(String id);
}
