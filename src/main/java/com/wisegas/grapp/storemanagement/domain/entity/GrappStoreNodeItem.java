package com.wisegas.grapp.storemanagement.domain.entity;

import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.storemanagement.domain.value.GrappStoreNodeItemId;
import com.wisegas.grapp.storemanagement.domain.value.Item;

import javax.persistence.*;

@Entity
public class GrappStoreNodeItem extends SimpleEntity<GrappStoreNodeItemId> {
   @EmbeddedId
   private GrappStoreNodeItemId id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappStoreNode grappStoreNode;

   private Item item;

   public GrappStoreNodeItem(GrappStoreNode grappStoreNode, Item item) {
      id = GrappStoreNodeItemId.generate();
      setGrappStoreNode(grappStoreNode);
      setItem(item);
   }

   protected GrappStoreNodeItem() {

   }

   @Override
   public GrappStoreNodeItemId getId() {
      return id;
   }

   public Item getItem() {
      return item;
   }

   private void setGrappStoreNode(GrappStoreNode node) {
      this.grappStoreNode = node;
   }

   private void setItem(Item item) {
      this.item = item;
   }
}
