package com.wisegas.shoppinglist.service.dto;

import com.wisegas.common.lang.dto.BaseDto;
import com.wisegas.common.lang.value.IdName;

import java.util.List;

public class ShopperDto extends BaseDto {

   private List<IdName> lists;

   public List<IdName> getLists() {
      return lists;
   }

   public void setLists(List<IdName> lists) {
      this.lists = lists;
   }
}
