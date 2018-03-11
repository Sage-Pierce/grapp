package org.codegas.stores.service_impl.api_impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.lang.annotation.ApplicationService;
import org.codegas.commons.lang.annotation.Transactional;
import org.codegas.commons.lang.spacial.GeoPoint;
import org.codegas.commons.lang.value.Email;
import org.codegas.stores.domain.entity.StoreManager;
import org.codegas.stores.domain.repository.StoreManagerRepository;
import org.codegas.stores.service.api.StoreManagerService;
import org.codegas.stores.service.dto.StoreDto;
import org.codegas.stores.service.dto.StoreManagerDto;
import org.codegas.stores.service_impl.factory.StoreDtoFactory;
import org.codegas.stores.service_impl.factory.StoreManagerDtoFactory;

@Named
@Singleton
@Transactional
@ApplicationService
public class StoreManagerServiceImpl implements StoreManagerService {

    private final StoreManagerRepository storeManagerRepository;

    @Inject
    public StoreManagerServiceImpl(StoreManagerRepository storeManagerRepository) {
        this.storeManagerRepository = storeManagerRepository;
    }

    @Override
    public StoreManagerDto loadByEmail(Email email) {
        return StoreManagerDtoFactory.createDto(storeManagerRepository.findByEmail(email).orElseGet(() -> persistStoreManagerWithEmail(email)));
    }

    @Override
    public StoreDto addStore(Email email, String name, GeoPoint location) {
        return StoreDtoFactory.createDto(storeManagerRepository.get(email).addStore(name, location));
    }

    private StoreManager persistStoreManagerWithEmail(Email email) {
        return storeManagerRepository.add(new StoreManager(email));
    }
}
