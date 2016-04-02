package com.wisegas.grapp.domain.value;

import com.wisegas.grapp.domain.entity.GrappItem;
import com.wisegas.grapp.domain.entity.GrappStoreNode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class GrappStoreNodeItem implements Serializable {

   @Id
   @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
   private GrappStoreNode grappStoreNode;

   @Id
   @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
   private GrappItem grappItem;

   public GrappStoreNodeItem(GrappStoreNode grappStoreNode, GrappItem grappItem) {
      setGrappStoreNode(grappStoreNode);
      setGrappItem(grappItem);
   }

   protected GrappStoreNodeItem() {

   }

   public GrappStoreNode getGrappStoreNode() {
      return grappStoreNode;
   }

   public GrappItem getGrappItem() {
      return grappItem;
   }

   private void setGrappStoreNode(GrappStoreNode grappStoreNode) {
      this.grappStoreNode = grappStoreNode;
   }

   private void setGrappItem(GrappItem grappItem) {
      this.grappItem = grappItem;
   }
}
