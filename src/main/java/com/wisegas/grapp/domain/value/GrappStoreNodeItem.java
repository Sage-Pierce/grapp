package com.wisegas.grapp.domain.value;

import com.wisegas.grapp.domain.entity.GrappItem;
import com.wisegas.grapp.domain.entity.GrappStore;
import com.wisegas.grapp.domain.entity.GrappStoreNode;
import com.wisegas.persistence.jpa.value.DBObject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class GrappStoreNodeItem extends DBObject {
   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappStore grappStore;

   @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
   private GrappStoreNode grappStoreNode;

   @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
   private GrappItem grappItem;

   public GrappStoreNodeItem(GrappStoreNode grappStoreNode, GrappItem grappItem) {
      setGrappStoreNode(grappStoreNode);
      setGrappItem(grappItem);
   }

   protected GrappStoreNodeItem() {

   }

   public GrappStore getGrappStore() {
      return grappStore;
   }

   public GrappStoreNode getGrappStoreNode() {
      return grappStoreNode;
   }

   public GrappItem getGrappItem() {
      return grappItem;
   }

   private void setGrappStoreNode(GrappStoreNode grappStoreNode) {
      this.grappStore = grappStoreNode.getGrappStore();
      this.grappStoreNode = grappStoreNode;
   }

   private void setGrappItem(GrappItem grappItem) {
      this.grappItem = grappItem;
   }
}
