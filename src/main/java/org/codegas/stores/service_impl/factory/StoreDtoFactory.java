package org.codegas.stores.service_impl.factory;

import org.codegas.stores.domain.entity.Store;
import org.codegas.stores.service.dto.StoreDto;

public final class StoreDtoFactory {

    private StoreDtoFactory() {

    }

    public static StoreDto createDto(Store store) {
        StoreDto storeDto = new StoreDto();
        storeDto.setId(store.getId().toString());
        storeDto.setName(store.getName());
        storeDto.setLocation(store.getLocation());
        storeDto.setLayoutId(store.getStoreLayout().getId().toString());
        return storeDto;
    }
}
