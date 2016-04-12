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

   private String code;

   public GrappStoreNodeItem(GrappStoreNode grappStoreNode, String code, String name) {
      id = GrappStoreNodeItemId.generate();
      setGrappStoreNode(grappStoreNode);
      setName(name);
      setCode(code);
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

   private void setCode(String code) {
      this.code = code;
   }
}
