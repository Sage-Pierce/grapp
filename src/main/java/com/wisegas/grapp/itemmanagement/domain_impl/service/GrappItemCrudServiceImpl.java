package com.wisegas.grapp.itemmanagement.domain_impl.service;

import com.wisegas.common.lang.exception.EntityConflictException;
import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem;
import com.wisegas.grapp.itemmanagement.domain.repository.GrappItemRepository;
import com.wisegas.grapp.itemmanagement.domain.service.GrappItemCreationService;
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemId;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Named
@Singleton
public class GrappItemCrudServiceImpl implements GrappItemCreationService {

   private final GrappItemRepository grappItemRepository;

   @Inject
   public GrappItemCrudServiceImpl(GrappItemRepository grappItemRepository) {
      this.grappItemRepository = grappItemRepository;
   }

   @Override
   public GrappItem createGeneralItem(String name) {
      assertItemNameUniqueness(name);
      return grappItemRepository.add(new GrappItem(name));
   }

   @Override
   public GrappItem createSubItem(GrappItemId superItemId, String name) {
      assertItemNameUniqueness(name);
      GrappItem superItem = grappItemRepository.get(superItemId);
      return superItem.addSubItem(name);
   }

   private void assertItemNameUniqueness(String name) {
      Optional<GrappItem> foundGrappItem = grappItemRepository.findByName(name);
      if (foundGrappItem.isPresent()) {
         throw new EntityConflictException("An Item with this name already exists: " + name + ", at " + stringifyItemHierarchy(foundGrappItem.get()));
      }
   }

   private String stringifyItemHierarchy(GrappItem grappItem) {
      List<GrappItem> itemHierarchy = new ArrayList<>();
      itemHierarchy.add(grappItem);
      while (!grappItem.isGeneralItem()) {
         itemHierarchy.add(grappItem = grappItem.getSuperItem());
      }
      Collections.reverse(itemHierarchy);
      return itemHierarchy.stream().map(GrappItem::getName).collect(Collectors.joining(" -> "));
   }
}
