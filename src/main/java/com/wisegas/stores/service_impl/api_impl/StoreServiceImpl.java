package com.wisegas.stores.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.common.lang.spacial.GeoPoint;
import com.wisegas.stores.domain.entity.Store;
import com.wisegas.stores.domain.repository.StoreRepository;
import com.wisegas.stores.domain.value.StoreId;
import com.wisegas.stores.service.api.StoreService;
import com.wisegas.stores.service.dto.StoreDto;
import com.wisegas.stores.service_impl.factory.StoreDtoFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

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
   public List<StoreDto> getAll() {
      return storeRepository.getAll().stream().map(StoreDtoFactory::createDto).collect(Collectors.toList());
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