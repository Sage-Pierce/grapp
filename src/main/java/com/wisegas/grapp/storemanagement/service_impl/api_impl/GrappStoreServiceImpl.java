package com.wisegas.grapp.storemanagement.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.grapp.storemanagement.domain.entity.GrappStore;
import com.wisegas.grapp.storemanagement.domain.repository.GrappStoreRepository;
import com.wisegas.grapp.storemanagement.domain.value.GrappStoreId;
import com.wisegas.grapp.storemanagement.service.api.GrappStoreService;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreDTOO;
import com.wisegas.grapp.storemanagement.service_impl.factory.GrappStoreDTOFactory;

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
   public GrappStoreDTOO create(String name, GeoPoint location) {
      return GrappStoreDTOFactory.createDTO(grappStoreRepository.add(new GrappStore(name, location)));
   }

   @Override
   public List<GrappStoreDTOO> getAll() {
      return grappStoreRepository.getAll().stream().map(GrappStoreDTOFactory::createDTO).collect(Collectors.toList());
   }

   @Override
   public GrappStoreDTOO get(String id) {
      return GrappStoreDTOFactory.createDTO(grappStoreRepository.get(GrappStoreId.fromString(id)));
   }

   @Override
   public GrappStoreDTOO update(String id, String name, GeoPoint location) {
      GrappStore grappStore = grappStoreRepository.get(GrappStoreId.fromString(id));
      grappStore.setName(name);
      grappStore.setLocation(location);
      return GrappStoreDTOFactory.createDTO(grappStore);
   }

   @Override
   public void delete(String id) {
      grappStoreRepository.remove(grappStoreRepository.get(GrappStoreId.fromString(id)));
   }
}
