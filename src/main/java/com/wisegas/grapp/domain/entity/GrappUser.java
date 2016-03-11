package com.wisegas.grapp.domain.entity;

import com.wisegas.common.persistence.jpa.entity.NamedEntity;
import com.wisegas.grapp.domain.value.GrappUserID;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"GrappUser\"")
public class GrappUser extends NamedEntity<GrappUserID> {
   @EmbeddedId
   private GrappUserID id;

   private String email;

   private String avatar;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappUser", orphanRemoval = true)
   private List<GrappUserList> grappUserLists = new ArrayList<>();

   public GrappUser(String email, String name, String avatar) {
      id = GrappUserID.generate();
      setEmail(email);
      setName(name);
      setAvatar(avatar);
   }

   protected GrappUser() {

   }

   @Override
   public GrappUserID getId() {
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
