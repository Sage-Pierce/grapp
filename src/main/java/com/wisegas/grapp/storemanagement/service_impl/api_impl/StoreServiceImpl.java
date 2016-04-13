package com.wisegas.grapp.storemanagement.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.grapp.storemanagement.domain.entity.Store;
import com.wisegas.grapp.storemanagement.domain.repository.StoreRepository;
import com.wisegas.grapp.storemanagement.domain.value.StoreId;
import com.wisegas.grapp.storemanagement.service.api.StoreService;
import com.wisegas.grapp.storemanagement.service.dto.StoreDto;
import com.wisegas.grapp.storemanagement.service_impl.factory.StoreDtoFactory;

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
   public StoreDto create(String name, GeoPoint location) {
      return StoreDtoFactory.createDto(storeRepository.add(new Store(name, location)));
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
      storeRepository.remove(storeRepository.get(StoreId.fromString(id)));
   }
}
