package com.wisegas.grapp.usermanagement.domain.entity;

import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.usermanagement.domain.value.GrappUserListItemId;
import com.wisegas.grapp.usermanagement.domain.value.Item;

import javax.persistence.*;

@Entity
public class GrappUserListItem extends SimpleEntity<GrappUserListItemId> {
   @EmbeddedId
   private GrappUserListItemId id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappUserList grappUserList;

   private Item item;

   public GrappUserListItem(GrappUserList grappUserList, Item item) {
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

   public Item getItem() {
      return item;
   }

   private void setGrappUserList(GrappUserList grappUserList) {
      this.grappUserList = grappUserList;
   }

   private void setItem(Item item) {
      this.item = item;
   }
}
