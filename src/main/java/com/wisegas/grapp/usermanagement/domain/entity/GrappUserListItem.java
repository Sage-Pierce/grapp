package com.wisegas.grapp.usermanagement.domain.entity;

import com.wisegas.common.persistence.jpa.entity.NamedEntity;
import com.wisegas.grapp.usermanagement.domain.value.GrappUserListItemId;

import javax.persistence.*;

@Entity
public class GrappUserListItem extends NamedEntity<GrappUserListItemId> {
   @EmbeddedId
   private GrappUserListItemId id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappUserList grappUserList;

   private String code;

   public GrappUserListItem(GrappUserList grappUserList, String code, String name) {
      id = GrappUserListItemId.generate();
      setGrappUserList(grappUserList);
      setName(name);
      setCode(code);
   }

   protected GrappUserListItem() {

   }

   @Override
   public GrappUserListItemId getId() {
      return id;
   }

   private void setGrappUserList(GrappUserList grappUserList) {
      this.grappUserList = grappUserList;
   }

   private void setCode(String code) {
      this.code = code;
   }
}
