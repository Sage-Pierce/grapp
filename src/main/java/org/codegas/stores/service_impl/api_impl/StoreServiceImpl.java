package org.codegas.stores.service_impl.api_impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.common.lang.annotation.ApplicationService;
import org.codegas.common.lang.annotation.Transactional;
import org.codegas.common.lang.spacial.GeoPoint;
import org.codegas.stores.domain.entity.Store;
import org.codegas.stores.domain.repository.StoreRepository;
import org.codegas.stores.domain.value.StoreId;
import org.codegas.stores.service.api.StoreService;
import org.codegas.stores.service.dto.StoreDto;
import org.codegas.stores.service_impl.factory.StoreDtoFactory;

@Named
@Singleton
@Transactional
@ApplicationService
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Inject
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<StoreDto> get() {
        return storeRepository.get().stream().map(StoreDtoFactory::createDto).collect(Collectors.toList());
    }

    @Override
    public StoreDto get(String id) {
        return StoreDtoFactory.createDto(storeRepository.get(StoreId.fromString(id)));
    }

    @Override
    public StoreDto update(String id, String name, GeoPoint location) {
        Store store = storeRepository.get(StoreId.fromString(id));
        store.setName(name);
        store.setLocation(location);
        return StoreDtoFactory.createDto(store);
    }

    @Override
    public void delete(String id) {
        storeRepository.remove(StoreId.fromString(id));
    }
}
