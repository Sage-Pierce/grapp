package com.wisegas.grapp.storemanagement.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.grapp.storemanagement.domain.entity.GrappStore;
import com.wisegas.grapp.storemanagement.domain.repository.GrappStoreRepository;
import com.wisegas.grapp.storemanagement.domain.value.GrappStoreId;
import com.wisegas.grapp.storemanagement.service.api.GrappStoreService;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreDto;
import com.wisegas.grapp.storemanagement.service_impl.factory.GrappStoreDtoFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Singleton
@Transactional
@ApplicationService
public class GrappStoreServiceImpl implements GrappStoreService {

   private final GrappStoreRepository grappStoreRepository;

   @Inject
   public GrappStoreServiceImpl(GrappStoreRepository grappStoreRepository) {
      this.grappStoreRepository = grappStoreRepository;
   }

   @Override
   public GrappStoreDto create(String name, GeoPoint location) {
      return GrappStoreDtoFactory.createDto(grappStoreRepository.add(new GrappStore(name, location)));
   }

   @Override
   public List<GrappStoreDto> getAll() {
      return grappStoreRepository.getAll().stream().map(GrappStoreDtoFactory::createDto).collect(Collectors.toList());
   }

   @Override
   public GrappStoreDto get(String id) {
      return GrappStoreDtoFactory.createDto(grappStoreRepository.get(GrappStoreId.fromString(id)));
   }

   @Override
   public GrappStoreDto update(String id, String name, GeoPoint location) {
      GrappStore grappStore = grappStoreRepository.get(GrappStoreId.fromString(id));
      grappStore.setName(name);
      grappStore.setLocation(location);
      return GrappStoreDtoFactory.createDto(grappStore);
   }

   @Override
   public void delete(String id) {
      grappStoreRepository.remove(grappStoreRepository.get(GrappStoreId.fromString(id)));
   }
}
