package com.wisegas.grapp.usermanagement.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.util.Base64;

@Embeddable
public class GrappUserId extends EntityId {
   @Basic
   private String email;

   public static GrappUserId fromString(String string) {
      return new GrappUserId(new String(Base64.getDecoder().decode(string)));
   }

   public GrappUserId(String email) {
      this.email = email;
   }

   protected GrappUserId() {

   }

   public String getEmail() {
      return email;
   }

   @Override
   protected Object idHash() {
      return Base64.getEncoder().encodeToString(email.getBytes());
   }
}
