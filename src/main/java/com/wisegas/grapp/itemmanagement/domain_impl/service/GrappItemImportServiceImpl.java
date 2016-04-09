package com.wisegas.grapp.itemmanagement.domain_impl.service;

import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem;
import com.wisegas.grapp.itemmanagement.domain.repository.GrappItemRepository;
import com.wisegas.grapp.itemmanagement.domain.service.GrappItemImportService;
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCode;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;

@Named
@Singleton
public class GrappItemImportServiceImpl implements GrappItemImportService {

   private final GrappItemRepository grappItemRepository;

   @Inject
   public GrappItemImportServiceImpl(GrappItemRepository grappItemRepository) {
      this.grappItemRepository = grappItemRepository;
   }

   @Override
   public GrappItem importGeneralItem(GrappItemCode code, String name) {
      Optional<GrappItem> reimportedGeneralItem = validateReimport(code, name);
      GrappItem generalItem = reimportedGeneralItem.isPresent() ? reimportedGeneralItem.get() : grappItemRepository.add(new GrappItem(name));
      generalItem.addCode(code);
      return generalItem;
   }

   @Override
   public GrappItem importSubItem(GrappItemCode superCode, GrappItemCode code, String name) {
      Optional<GrappItem> reimportedSubItem = validateReimport(code, name);
      GrappItem subItem = reimportedSubItem.isPresent() ? reimportedSubItem.get() : importSubItemToSuperItem(superCode, name);
      subItem.addCode(code);
      return subItem;
   }

   @Override
   public GrappItem importSubItem(GrappItemCode superCode, String name) {
      Optional<GrappItem> reimportedSubItem = grappItemRepository.findByName(name);
      return reimportedSubItem.isPresent() ? reimportedSubItem.get() : importSubItemToSuperItem(superCode, name);
   }

   private GrappItem importSubItemToSuperItem(GrappItemCode superCode, String name) {
      Optional<GrappItem> foundSuperItem = grappItemRepository.findByCode(superCode);
      if (foundSuperItem.isPresent()) {
         return foundSuperItem.get().addSubItem(name);
      }
      else {
         throw new IllegalStateException("Cannot Import Sub-Item due to non-existent Super Item: " + superCode + ", " + name);
      }
   }

   private Optional<GrappItem> validateReimport(GrappItemCode code, String name) {
      Optional<GrappItem> foundItemByCode = grappItemRepository.findByCode(code);
      Optional<GrappItem> foundItemByName = grappItemRepository.findByName(name);
      if (foundItemByCode.isPresent() && foundItemByName.isPresent() && !foundItemByCode.equals(foundItemByName)) {
         throw new IllegalStateException("Cannot Import conflicting Items: " + foundItemByCode.get().getId() + ", " + foundItemByName.get().getId() + ", " + code + ", " + name);
      }
      else {
         return foundItemByCode.isPresent() ? foundItemByCode : foundItemByName;
      }
   }
}
