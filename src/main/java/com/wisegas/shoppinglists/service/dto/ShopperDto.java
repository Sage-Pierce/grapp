package com.wisegas.shoppinglists.service.dto;

import com.wisegas.common.lang.value.IdName;

import java.util.List;

public class ShopperDto {

   private String email;
   private List<IdName> lists;

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public List<IdName> getLists() {
      return lists;
   }

   public void setLists(List<IdName> lists) {
      this.lists = lists;
   }
}
