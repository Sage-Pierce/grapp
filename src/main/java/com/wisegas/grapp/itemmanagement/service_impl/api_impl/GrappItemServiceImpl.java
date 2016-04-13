package com.wisegas.grapp.itemmanagement.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.grapp.itemmanagement.domain.repository.GrappItemRepository;
import com.wisegas.grapp.itemmanagement.domain.service.GrappItemCreationService;
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCode;
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCodeType;
import com.wisegas.grapp.itemmanagement.service.api.GrappItemService;
import com.wisegas.grapp.itemmanagement.service.dto.GrappItemDTOO;
import com.wisegas.grapp.itemmanagement.service_impl.factory.GrappItemDtoFactory;

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
   private final GrappItemRepository grappItemRepository;

   @Inject
   public GrappItemServiceImpl(GrappItemCreationService grappItemCreationService, GrappItemRepository grappItemRepository) {
      this.grappItemCreationService = grappItemCreationService;
      this.grappItemRepository = grappItemRepository;
   }

   @Override
   public GrappItemDTOO createGeneralItem(String codeType, String code, String name) {
      return GrappItemDtoFactory.createDTO(grappItemCreationService.createGeneralItem(createGrappItemCode(codeType, code), name));
   }

   @Override
   public GrappItemDTOO createSubItem(String superItemId, String codeType, String code, String name) {
      return GrappItemDtoFactory.createDTO(grappItemCreationService.createSubItem(GrappItemCode.fromString(superItemId), createGrappItemCode(codeType, code), name));
   }

   @Override
   public List<GrappItemDTOO> getAll() {
      return grappItemRepository.getAll().stream().map(GrappItemDtoFactory::createDTO).collect(Collectors.toList());
   }

   @Override
   public List<GrappItemDTOO> getGeneralItems() {
      return grappItemRepository.getGeneralItems().stream().map(GrappItemDtoFactory::createDTO).collect(Collectors.toList());
   }

   @Override
   public GrappItemDTOO get(String id) {
      return GrappItemDtoFactory.createDTO(grappItemRepository.get(GrappItemCode.fromString(id)));
   }

   @Override
   public void delete(String id) {
      grappItemRepository.remove(grappItemRepository.get(GrappItemCode.fromString(id)));
   }

   private GrappItemCode createGrappItemCode(String codeType, String code) {
      return new GrappItemCode(GrappItemCodeType.valueOf(codeType), code);
   }
}
