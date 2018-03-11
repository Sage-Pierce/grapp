package org.codegas.stores.service.api;

import java.util.List;

import org.codegas.commons.lang.spacial.GeoPoint;
import org.codegas.stores.service.dto.StoreDto;

public interface StoreService {

    List<StoreDto> get();

    StoreDto get(String id);

    StoreDto update(String id, String name, GeoPoint location);

    void delete(String id);
}
