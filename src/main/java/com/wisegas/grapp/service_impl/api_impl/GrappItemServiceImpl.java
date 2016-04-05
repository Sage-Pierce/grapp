package com.wisegas.grapp.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.grapp.domain.repository.GrappItemRepository;
import com.wisegas.grapp.domain.service.GrappItemCreationService;
import com.wisegas.grapp.domain.service.GrappItemUpdateService;
import com.wisegas.grapp.domain.value.GrappItemId;
import com.wisegas.grapp.service.api.GrappItemService;
import com.wisegas.grapp.service.dto.GrappItemDTO;
import com.wisegas.grapp.service_impl.factory.GrappItemDTOFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Singleton
@Transactional
@ApplicationService
public class GrappItemServiceImpl implements GrappItemService {

   private final GrappItemCreationService grappItemCreationService;
   private final GrappItemUpdateService grappItemUpdateService;
   private final GrappItemRepository grappItemRepository;

   @Inject
   public GrappItemServiceImpl(GrappItemCreationService grappItemCreationService, GrappItemUpdateService grappItemUpdateService, GrappItemRepository grappItemRepository) {
      this.grappItemCreationService = grappItemCreationService;
      this.grappItemUpdateService = grappItemUpdateService;
      this.grappItemRepository = grappItemRepository;
   }

   @Override
   public GrappItemDTO createGeneralItem(String name) {
      return GrappItemDTOFactory.createDTO(grappItemCreationService.createGeneralItem(name));
   }

   @Override
   public GrappItemDTO createSubItem(String superItemId, String name) {
      return GrappItemDTOFactory.createDTO(grappItemCreationService.createSubItem(GrappItemId.fromString(superItemId), name));
   }

   @Override
   public List<GrappItemDTO> getAll() {
      return grappItemRepository.getAll().stream().map(GrappItemDTOFactory::createDTO).collect(Collectors.toList());
   }

   @Override
   public List<GrappItemDTO> getGeneralItems() {
      return grappItemRepository.getGeneralItems().stream().map(GrappItemDTOFactory::createDTO).collect(Collectors.toList());
   }

   @Override
   public GrappItemDTO get(String id) {
      return GrappItemDTOFactory.createDTO(grappItemRepository.get(GrappItemId.fromString(id)));
   }

   @Override
   public GrappItemDTO update(String id, String name) {
      return GrappItemDTOFactory.createDTO(grappItemUpdateService.updateName(GrappItemId.fromString(id), name));
   }

   @Override
   public void delete(String id) {
      grappItemRepository.remove(grappItemRepository.get(GrappItemId.fromString(id)));
   }
}
