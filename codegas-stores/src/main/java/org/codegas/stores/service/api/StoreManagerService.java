package org.codegas.stores.service.api;

import java.util.Optional;

import org.codegas.commons.lang.spacial.GeoPoint;
import org.codegas.commons.lang.value.PrincipalName;
import org.codegas.stores.service.dto.StoreDto;
import org.codegas.stores.service.dto.StoreManagerDto;

public interface StoreManagerService {

    StoreManagerDto create(PrincipalName name);

    Optional<StoreManagerDto> find(PrincipalName name);

    StoreDto addStore(PrincipalName managerName, String storeName, GeoPoint location);
}
