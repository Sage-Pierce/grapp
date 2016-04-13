package com.wisegas.grapp.itemmanagement.domain.entity;

import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.itemmanagement.domain.value.Code;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Item extends SimpleEntity<Code> {
   @EmbeddedId
   private Code id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
   private Item superItem;

   @Column(unique = true)
   private String name;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "superItem")
   private List<Item> subItems = new ArrayList<>();

   public Item(Code code, String name) {
      this(null, code, name);
   }

   protected Item() {

   }

   private Item(Item superItem, Code code, String name) {
      id = code;
      setSuperItem(superItem);
      setName(name);
   }

   public List<Item> getHierarchy() {
      Item hierarchicalItem = this;
      List<Item> hierarchy = new ArrayList<>();
      hierarchy.add(hierarchicalItem);
      while (!hierarchicalItem.isGeneralItem()) {
         hierarchy.add(hierarchicalItem = hierarchicalItem.getSuperItem());
      }
      Collections.reverse(hierarchy);
      return hierarchy;
   }

   @Override
   public Code getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<Item> getSubItems() {
      return subItems;
   }

   public Item addSubItem(Code code, String name) {
      Item subItem = new Item(this, code, name);
      subItems.add(subItem);
      return subItem;
   }

   public boolean isGeneralItem() {
      return superItem == null;
   }

   public Item getSuperItem() {
      return superItem;
   }

   private void setSuperItem(Item superItem) {
      this.superItem = superItem;
   }
}
