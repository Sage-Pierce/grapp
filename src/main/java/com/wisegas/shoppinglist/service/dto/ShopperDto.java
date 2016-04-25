package com.wisegas.shoppinglist.service.dto;

import com.wisegas.common.lang.value.Email;
import com.wisegas.common.lang.value.IdName;

import java.util.List;

public class ShopperDto {

   private Email email;
   private List<IdName> lists;

   public Email getEmail() {
      return email;
   }

   public void setEmail(Email email) {
      this.email = email;
   }

   public List<IdName> getLists() {
      return lists;
   }

   public void setLists(List<IdName> lists) {
      this.lists = lists;
   }
}
