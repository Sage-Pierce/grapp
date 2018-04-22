package org.codegas.stores.service_impl.factory;

import java.util.stream.Collectors;

import org.codegas.stores.domain.entity.StoreManager;
import org.codegas.stores.service.dto.StoreManagerDto;

public final class StoreManagerDtoFactory {

    private StoreManagerDtoFactory() {

    }

    public static StoreManagerDto createDto(StoreManager storeManager) {
        StoreManagerDto storeManagerDto = new StoreManagerDto();
        storeManagerDto.setEmail(storeManager.getEmail().toString());
        storeManagerDto.setStores(storeManager.getStores().stream().map(StoreDtoFactory::createDto).collect(Collectors.toList()));
        return storeManagerDto;
    }
}
