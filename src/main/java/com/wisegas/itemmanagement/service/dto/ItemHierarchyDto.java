package com.wisegas.itemmanagement.service.dto;

import com.wisegas.common.lang.dto.NamedDto;
import com.wisegas.common.lang.value.IdName;

import java.util.List;

public class ItemHierarchyDto extends NamedDto {

   private List<IdName> hierarchy;

   public List<IdName> getHierarchy() {
      return hierarchy;
   }

   public void setHierarchy(List<IdName> hierarchy) {
      this.hierarchy = hierarchy;
   }
}
