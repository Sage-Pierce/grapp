package com.wisegas.grapp.domain_impl.service;

import com.wisegas.grapp.domain.entity.GrappItem;
import com.wisegas.grapp.domain.repository.GrappItemRepository;
import com.wisegas.grapp.domain.service.GrappItemCreationService;
import com.wisegas.grapp.domain.service.GrappItemUpdateService;
import com.wisegas.grapp.domain.value.GrappItemID;

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
public class GrappItemCrudServiceImpl implements GrappItemCreationService, GrappItemUpdateService {

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
   public GrappItem createSubItem(GrappItemID superItemId, String name) {
      assertItemNameUniqueness(name);
      GrappItem superItem = grappItemRepository.get(superItemId);
      return superItem.addSubItem(name);
   }

   @Override
   public GrappItem updateName(GrappItemID id, String name) {
      GrappItem grappItem = grappItemRepository.get(id);
      if (!grappItem.getName().equals(name)) {
         assertItemNameUniqueness(name);
         grappItem.setName(name);
      }
      return grappItem;
   }

   private void assertItemNameUniqueness(String name) {
      Optional<GrappItem> foundGrappItem = grappItemRepository.findWithName(name);
      if (foundGrappItem.isPresent()) {
         throw new RuntimeException("An Item with this name already exists: " + name + ", at " + stringifyItemHierarchy(foundGrappItem.get()));
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
