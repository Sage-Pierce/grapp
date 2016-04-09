package com.wisegas.grapp.storemanagement.domain.entity;

import com.wisegas.common.persistence.jpa.entity.NamedEntity;
import com.wisegas.grapp.storemanagement.domain.value.GrappStoreNodeItemId;

import javax.persistence.*;

@Entity
public class GrappStoreNodeItem extends NamedEntity<GrappStoreNodeItemId> {
   @EmbeddedId
   private GrappStoreNodeItemId id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappStoreNode grappStoreNode;

   public GrappStoreNodeItem(GrappStoreNode grappStoreNode, String name) {
      id = GrappStoreNodeItemId.generate();
      setGrappStoreNode(grappStoreNode);
      setName(name);
   }

   protected GrappStoreNodeItem() {

   }

   @Override
   public GrappStoreNodeItemId getId() {
      return id;
   }

   private void setGrappStoreNode(GrappStoreNode node) {
      this.grappStoreNode = node;
   }
}
