package com.wisegas.grapp.service_impl.api;

import com.wisegas.grapp.domain.entity.GrappStore;
import com.wisegas.grapp.domain.entity.GrappUser;
import com.wisegas.grapp.domain.repository.GrappStoreRepository;
import com.wisegas.grapp.domain.repository.GrappUserRepository;
import com.wisegas.grapp.domain.value.GrappStoreID;
import com.wisegas.grapp.domain.value.GrappUserID;
import com.wisegas.grapp.service.api.GrappStoreService;
import com.wisegas.grapp.service.dto.GrappStoreDTO;
import com.wisegas.grapp.service_impl.factory.GrappStoreDTOFactory;
import com.wisegas.lang.GeoPoint;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

@Named
@Singleton
@Transactional
public class GrappStoreServiceImpl implements GrappStoreService {

   private final GrappStoreRepository grappStoreRepository;
   private final GrappUserRepository grappUserRepository;

   @Inject
   public GrappStoreServiceImpl(GrappStoreRepository grappStoreRepository, GrappUserRepository grappUserRepository) {
      this.grappStoreRepository = grappStoreRepository;
      this.grappUserRepository = grappUserRepository;
   }

   @Override
   public GrappStoreDTO createForOwner(String ownerID, String name, GeoPoint location) {
      GrappUser grappUser = grappUserRepository.findByID(GrappUserID.fromString(ownerID));
      GrappStore grappStore = grappUser.addGrappStore(name, location);
      return GrappStoreDTOFactory.createDTO(grappStore);
   }

   @Override
   public List<GrappStoreDTO> findAllForOwner(String ownerID) {
      List<GrappStore> grappStores = grappStoreRepository.findAllForOwner(GrappUserID.fromString(ownerID));
      return GrappStoreDTOFactory.createDTOs(grappStores);
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
