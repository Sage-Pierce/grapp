package com.wisegas.grapp.itemmanagement.domain.entity;

import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class GrappItem extends SimpleEntity<GrappItemCode> {
   @EmbeddedId
   private GrappItemCode id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
   private GrappItem superItem;

   @Column(unique = true)
   private String name;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "superItem")
   private List<GrappItem> subItems = new ArrayList<>();

   public GrappItem(GrappItemCode code, String name) {
      this(null, code, name);
   }

   protected GrappItem() {

   }

   private GrappItem(GrappItem superItem, GrappItemCode code, String name) {
      id = code;
      setSuperItem(superItem);
      setName(name);
   }

   public List<GrappItem> getHierarchy() {
      GrappItem hierarchicalItem = this;
      List<GrappItem> hierarchy = new ArrayList<>();
      hierarchy.add(hierarchicalItem);
      while (!hierarchicalItem.isGeneralItem()) {
         hierarchy.add(hierarchicalItem = hierarchicalItem.getSuperItem());
      }
      Collections.reverse(hierarchy);
      return hierarchy;
   }

   @Override
   public GrappItemCode getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<GrappItem> getSubItems() {
      return subItems;
   }

   public GrappItem addSubItem(GrappItemCode code, String name) {
      GrappItem subItem = new GrappItem(this, code, name);
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
