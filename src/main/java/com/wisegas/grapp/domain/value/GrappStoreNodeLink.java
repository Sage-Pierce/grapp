package com.wisegas.grapp.domain.value;

import com.wisegas.grapp.domain.entity.GrappStoreNode;
import com.wisegas.persistence.jpa.value.DBObject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class GrappStoreNodeLink extends DBObject {
   @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
   private GrappStoreNode grappStoreNode1;

   @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
   private GrappStoreNode grappStoreNode2;

   public GrappStoreNodeLink(GrappStoreNode grappStoreNode1, GrappStoreNode grappStoreNode2) {
      setGrappStoreNode1(grappStoreNode1);
      setGrappStoreNode2(grappStoreNode2);
   }

   protected GrappStoreNodeLink() {

   }

   public GrappStoreNode getGrappStoreNode1() {
      return grappStoreNode1;
   }

   public GrappStoreNode getGrappStoreNode2() {
      return grappStoreNode2;
   }

   private void setGrappStoreNode1(GrappStoreNode grappStoreNode1) {
      this.grappStoreNode1 = grappStoreNode1;
   }

   private void setGrappStoreNode2(GrappStoreNode grappStoreNode2) {
      this.grappStoreNode2 = grappStoreNode2;
   }
}
