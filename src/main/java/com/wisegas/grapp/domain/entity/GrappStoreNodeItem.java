package com.wisegas.grapp.domain.entity;

import com.wisegas.common.lang.value.IdName;
import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.domain.value.GrappStoreNodeItemID;

import javax.persistence.*;

@Entity
public class GrappStoreNodeItem extends SimpleEntity<GrappStoreNodeItemID> {
   @EmbeddedId
   private GrappStoreNodeItemID id;

   @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
   private GrappStoreNode grappStoreNode;

   @SuppressWarnings({"JpaAttributeTypeInspection", "JpaModelReferenceInspection", "JpaDataSourceORMInspection"})
   @Embedded
   @AttributeOverrides({
       @AttributeOverride(name = "id", column = @Column(name = "itemId")),
       @AttributeOverride(name = "name", column = @Column(name = "itemName"))
   })
   private IdName item;

   public GrappStoreNodeItem(GrappStoreNode grappStoreNode, IdName item) {
      id = GrappStoreNodeItemID.generate();
      setGrappStoreNode(grappStoreNode);
      setItem(item);
   }

   protected GrappStoreNodeItem() {

   }

   @Override
   public GrappStoreNodeItemID getId() {
      return id;
   }

   public IdName getItem() {
      return item;
   }

   private void setGrappStoreNode(GrappStoreNode node) {
      this.grappStoreNode = node;
   }

   private void setItem(IdName item) {
      this.item = item;
   }
}
