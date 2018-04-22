package org.codegas.stores.service.api;

import org.codegas.commons.lang.spacial.GeoPoint;
import org.codegas.commons.lang.value.Email;
import org.codegas.stores.service.dto.StoreDto;
import org.codegas.stores.service.dto.StoreManagerDto;

public interface StoreManagerService {

    StoreManagerDto loadByEmail(Email email);

    StoreDto addStore(Email email, String name, GeoPoint location);
}
