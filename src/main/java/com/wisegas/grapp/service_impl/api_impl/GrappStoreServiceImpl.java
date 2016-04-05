package com.wisegas.grapp.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.grapp.domain.entity.GrappStore;
import com.wisegas.grapp.domain.repository.GrappStoreRepository;
import com.wisegas.grapp.domain.value.GrappStoreId;
import com.wisegas.grapp.service.api.GrappStoreService;
import com.wisegas.grapp.service.dto.GrappStoreDTO;
import com.wisegas.grapp.service_impl.factory.GrappStoreDTOFactory;

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
   public GrappStoreDTO create(String name, GeoPoint location) {
      return GrappStoreDTOFactory.createDTO(grappStoreRepository.add(new GrappStore(name, location)));
   }

   @Override
   public List<GrappStoreDTO> getAll() {
      return grappStoreRepository.getAll().stream().map(GrappStoreDTOFactory::createDTO).collect(Collectors.toList());
   }

   @Override
   public GrappStoreDTO get(String id) {
      return GrappStoreDTOFactory.createDTO(grappStoreRepository.get(GrappStoreId.fromString(id)));
   }

   @Override
   public GrappStoreDTO update(String id, String name, GeoPoint location) {
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
