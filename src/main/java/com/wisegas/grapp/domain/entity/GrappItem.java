package com.wisegas.grapp.domain.entity;

import com.wisegas.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.domain.value.GrappItemID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "\"GrappItem\"")
public class GrappItem extends SimpleEntity<GrappItemID> {
   @Embedded
   private GrappItemID id;

   private String name;

   public GrappItem(String name) {
      id = GrappItemID.generate();
      setName(name);
   }

   protected GrappItem() {

   }

   @Override
   public GrappItemID getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   private void setName(String name) {
      this.name = name;
   }
}
