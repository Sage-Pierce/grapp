package com.wisegas.grapp.domain.entity;

import com.wisegas.common.persistence.jpa.entity.NamedEntity;
import com.wisegas.grapp.domain.value.GrappUserListId;
import com.wisegas.grapp.domain.value.GrappUserListItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GrappUserList extends NamedEntity<GrappUserListId> {
   @EmbeddedId
   private GrappUserListId id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappUser grappUser;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappUserList", orphanRemoval = true)
   private List<GrappUserListItem> grappUserListItems = new ArrayList<>();

   public GrappUserList(GrappUser grappUser, String name) {
      id = GrappUserListId.generate();
      setGrappUser(grappUser);
      setName(name);
   }

   protected GrappUserList() {

   }

   @Override
   public GrappUserListId getId() {
      return id;
   }

   public GrappUser getGrappUser() {
      return grappUser;
   }

   public List<GrappUserListItem> getGrappUserListItems() {
      return grappUserListItems;
   }

   public GrappUserListItem addItem(GrappItem grappItem) {
      GrappUserListItem grappUserListItem = new GrappUserListItem(this, grappItem);
      grappUserListItems.add(grappUserListItem);
      return grappUserListItem;
   }

   private void setGrappUser(GrappUser grappUser) {
      this.grappUser = grappUser;
   }
}
