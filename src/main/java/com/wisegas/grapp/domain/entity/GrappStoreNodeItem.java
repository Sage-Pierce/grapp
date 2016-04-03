package com.wisegas.grapp.domain.entity;

import com.wisegas.common.lang.value.IdName;
import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.domain.value.GrappStoreNodeItemIDFUCK;

import javax.persistence.*;

@Entity
public class GrappStoreNodeItem extends SimpleEntity<GrappStoreNodeItemIDFUCK> {
   @EmbeddedId
   private GrappStoreNodeItemIDFUCK id;

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
      id = GrappStoreNodeItemIDFUCK.generate();
      setGrappStoreNode(grappStoreNode);
      setItem(item);
   }

   protected GrappStoreNodeItem() {

   }

   @Override
   public GrappStoreNodeItemIDFUCK getId() {
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
