package com.wisegas.grapp.service_impl.api;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.grapp.domain.entity.GrappStore;
import com.wisegas.grapp.domain.repository.GrappStoreRepository;
import com.wisegas.grapp.domain.value.GrappStoreID;
import com.wisegas.grapp.service.api.GrappStoreService;
import com.wisegas.grapp.service.dto.GrappStoreDTO;
import com.wisegas.grapp.service_impl.factory.GrappStoreDTOFactory;
import com.wisegas.common.lang.value.GeoPoint;

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
      GrappStore grappStore = new GrappStore(name, location);
      return GrappStoreDTOFactory.createDTO(grappStoreRepository.add(grappStore));
   }

   @Override
   public List<GrappStoreDTO> findAll() {
      List<GrappStore> grappStores = grappStoreRepository.getAll();
      return grappStores.stream().map(GrappStoreDTOFactory::createDTO).collect(Collectors.toList());
   }

   @Override
   public GrappStoreDTO findByID(String id) {
      return GrappStoreDTOFactory.createDTO(grappStoreRepository.findByID(GrappStoreID.fromString(id)));
   }

   @Override
   public GrappStoreDTO updateName(String id, String name) {
      GrappStore grappStore = grappStoreRepository.findByID(GrappStoreID.fromString(id));
      grappStore.setName(name);
      return GrappStoreDTOFactory.createDTO(grappStore);
   }

   @Override
   public GrappStoreDTO updateLocation(String id, GeoPoint location) {
      GrappStore grappStore = grappStoreRepository.findByID(GrappStoreID.fromString(id));
      grappStore.setLocation(location);
      return GrappStoreDTOFactory.createDTO(grappStore);
   }

   @Override
   public void deleteByID(String id) {
      grappStoreRepository.remove(grappStoreRepository.findByID(GrappStoreID.fromString(id)));
   }
}
