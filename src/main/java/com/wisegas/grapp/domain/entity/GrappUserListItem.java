package com.wisegas.grapp.domain.entity;

import com.wisegas.common.lang.value.IdName;
import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.domain.value.GrappUserListItemId;

import javax.persistence.*;

@Entity
public class GrappUserListItem extends SimpleEntity<GrappUserListItemId> {
   @EmbeddedId
   private GrappUserListItemId id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappUserList grappUserList;

   @SuppressWarnings({"JpaAttributeTypeInspection", "JpaModelReferenceInspection", "JpaDataSourceORMInspection"})
   @Embedded
   @AttributeOverrides({
      @AttributeOverride(name = "id", column = @Column(name = "itemId")),
      @AttributeOverride(name = "name", column = @Column(name = "itemName"))
   })
   private IdName item;

   public GrappUserListItem(GrappUserList grappUserList, IdName item) {
      id = GrappUserListItemId.generate();
      setGrappUserList(grappUserList);
      setItem(item);
   }

   protected GrappUserListItem() {

   }

   @Override
   public GrappUserListItemId getId() {
      return id;
   }

   public IdName getItem() {
      return item;
   }

   private void setGrappUserList(GrappUserList grappUserList) {
      this.grappUserList = grappUserList;
   }

   private void setItem(IdName item) {
      this.item = item;
   }
}
