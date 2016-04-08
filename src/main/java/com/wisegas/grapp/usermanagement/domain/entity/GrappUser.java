package com.wisegas.grapp.usermanagement.domain.entity;

import com.wisegas.common.persistence.jpa.entity.NamedEntity;
import com.wisegas.grapp.usermanagement.domain.value.GrappUserId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GrappUser extends NamedEntity<GrappUserId> {
   @EmbeddedId
   private GrappUserId id;

   private String email;

   private String avatar;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappUser", orphanRemoval = true)
   private List<GrappUserList> grappUserLists = new ArrayList<>();

   public GrappUser(String email, String name, String avatar) {
      id = GrappUserId.generate();
      setEmail(email);
      setName(name);
      setAvatar(avatar);
   }

   protected GrappUser() {

   }

   @Override
   public GrappUserId getId() {
      return id;
   }

   public String getEmail() {
      return email;
   }

   public String getAvatar() {
      return avatar;
   }

   public void setAvatar(String avatar) {
      this.avatar = avatar;
   }

   public GrappUserList addGrappUserList(String name) {
      GrappUserList grappUserList = new GrappUserList(this, name);
      grappUserLists.add(grappUserList);
      return grappUserList;
   }

   public List<GrappUserList> getGrappUserLists() {
      return grappUserLists;
   }

   private void setEmail(String email) {
      this.email = email;
   }
}
