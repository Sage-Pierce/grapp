package com.wisegas.grapp.domain.entity;

import com.wisegas.common.persistence.jpa.entity.NamedEntity;
import com.wisegas.grapp.domain.value.GrappItemID;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "\"GrappItem\"")
public class GrappItem extends NamedEntity<GrappItemID> {
   @EmbeddedId
   private GrappItemID id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
   private GrappItem superItem;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "superItem")
   private List<GrappItem> subItems = new ArrayList<>();

   public GrappItem(String name) {
      this(null, name);
   }

   protected GrappItem() {

   }

   private GrappItem(GrappItem superItem, String name) {
      id = GrappItemID.generate();
      setName(name);
      setSuperItem(superItem);
   }

   public GrappItem addSubItem(String name) {
      GrappItem subItem = new GrappItem(this, name);
      subItems.add(subItem);
      return subItem;
   }

   @Override
   public GrappItemID getId() {
      return id;
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

   public boolean isGeneralItem() {
      return superItem == null;
   }

   public GrappItem getSuperItem() {
      return superItem;
   }

   public List<GrappItem> getSubItems() {
      return subItems;
   }

   private void setSuperItem(GrappItem superItem) {
      this.superItem = superItem;
   }
}
