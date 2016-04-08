package com.wisegas.grapp.itemmanagement.domain.entity;

import com.wisegas.common.persistence.jpa.entity.NamedEntity;
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCode;
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemId;

import javax.persistence.*;
import java.util.*;

@Entity
public class GrappItem extends NamedEntity<GrappItemId> {
   @EmbeddedId
   private GrappItemId id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
   private GrappItem superItem;

   @ElementCollection
   private Set<GrappItemCode> codes = new HashSet<>();

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "superItem")
   private List<GrappItem> subItems = new ArrayList<>();

   public GrappItem(String name) {
      this(null, name);
   }

   protected GrappItem() {

   }

   private GrappItem(GrappItem superItem, String name) {
      id = GrappItemId.generate();
      setName(name);
      setSuperItem(superItem);
   }

   public List<GrappItem> getHierarchy() {
      List<GrappItem> hierarchy = new ArrayList<>();
      hierarchy.add(this);
      GrappItem hierarchicalItem = this;
      while (!hierarchicalItem.isGeneralItem()) {
         hierarchy.add(hierarchicalItem = hierarchicalItem.getSuperItem());
      }
      Collections.reverse(hierarchy);
      return hierarchy;
   }

   @Override
   public GrappItemId getId() {
      return id;
   }

   public Set<GrappItemCode> getCodes() {
      return codes;
   }

   public void addCode(GrappItemCode code) {
      codes.add(code);
   }

   public List<GrappItem> getSubItems() {
      return subItems;
   }

   public GrappItem addSubItem(String name) {
      GrappItem subItem = new GrappItem(this, name);
      subItems.add(subItem);
      return subItem;
   }

   public boolean isGeneralItem() {
      return superItem == null;
   }

   public GrappItem getSuperItem() {
      return superItem;
   }

   private void setSuperItem(GrappItem superItem) {
      this.superItem = superItem;
   }
}
