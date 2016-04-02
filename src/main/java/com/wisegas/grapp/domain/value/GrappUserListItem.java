package com.wisegas.grapp.domain.value;

import com.wisegas.grapp.domain.entity.GrappItem;
import com.wisegas.grapp.domain.entity.GrappUserList;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class GrappUserListItem implements Serializable {
   @Id
   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappUserList grappUserList;

   @Id
   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, optional = false)
   private GrappItem grappItem;

   public GrappUserListItem(GrappUserList grappUserList, GrappItem grappItem) {
      setGrappUserList(grappUserList);
      setGrappItem(grappItem);
   }

   protected GrappUserListItem() {

   }

   public GrappUserList getGrappUserList() {
      return grappUserList;
   }

   public GrappItem getGrappItem() {
      return grappItem;
   }

   private void setGrappUserList(GrappUserList grappUserList) {
      this.grappUserList = grappUserList;
   }

   private void setGrappItem(GrappItem grappItem) {
      this.grappItem = grappItem;
   }
}
