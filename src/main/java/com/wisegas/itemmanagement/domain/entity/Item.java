package com.wisegas.itemmanagement.domain.entity;

import com.wisegas.common.domain.entity.SimpleEntity;
import com.wisegas.itemmanagement.domain.value.Code;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item extends SimpleEntity<Code> {
   @EmbeddedId
   private Code primaryCode;

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

   private Item(Item superItem, Code primaryCode, String name) {
      this.primaryCode = primaryCode;
      setSuperItem(superItem);
      setName(name);
   }

   public List<Item> getLineage() {
      List<Item> lineage = new ArrayList<>();
      Item ancestor = this;
      lineage.add(ancestor);
      while (!ancestor.isGeneralItem()) {
         lineage.add(ancestor = ancestor.getSuperItem());
      }
      return lineage;
   }

   @Override
   public Code getId() {
      return getPrimaryCode();
   }

   public Code getPrimaryCode() {
      return primaryCode;
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
