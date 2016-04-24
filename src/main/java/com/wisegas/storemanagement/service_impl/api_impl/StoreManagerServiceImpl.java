package com.wisegas.storemanagement.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.common.lang.jpa.Email;
import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.storemanagement.domain.entity.StoreManager;
import com.wisegas.storemanagement.domain.repository.StoreManagerRepository;
import com.wisegas.storemanagement.service.api.StoreManagerService;
import com.wisegas.storemanagement.service.dto.StoreDto;
import com.wisegas.storemanagement.service.dto.StoreManagerDto;
import com.wisegas.storemanagement.service_impl.factory.StoreDtoFactory;
import com.wisegas.storemanagement.service_impl.factory.StoreManagerDtoFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

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
   public StoreManagerDto loadByEmail(String email) {
      return StoreManagerDtoFactory.createDto(storeManagerRepository.findByEmail(email).orElseGet(() -> persistStoreManagerWithEmail(email)));
   }

   @Override
   public StoreDto addStore(String id, String name, GeoPoint location) {
      return StoreDtoFactory.createDto(storeManagerRepository.get(Email.fromString(id)).addStore(name, location));
   }

   private StoreManager persistStoreManagerWithEmail(String email) {
      return storeManagerRepository.add(new StoreManager(Email.fromString(email)));
   }
}
