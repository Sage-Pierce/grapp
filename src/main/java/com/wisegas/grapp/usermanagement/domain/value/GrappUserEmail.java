package com.wisegas.grapp.usermanagement.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.util.Base64;

@Embeddable
public class GrappUserEmail extends EntityId {
   @Basic
   private String email;

   public static GrappUserEmail fromString(String string) {
      return new GrappUserEmail(new String(Base64.getDecoder().decode(string)));
   }

   public GrappUserEmail(String email) {
      this.email = email;
   }

   protected GrappUserEmail() {

   }

   public String getEmail() {
      return email;
   }

   @Override
   protected Object idHash() {
      return Base64.getEncoder().encodeToString(email.getBytes());
   }
}
