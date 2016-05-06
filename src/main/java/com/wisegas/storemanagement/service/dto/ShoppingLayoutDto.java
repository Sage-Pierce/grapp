package com.wisegas.storemanagement.service.dto;

import java.util.List;

public class ShoppingLayoutDto extends LayoutDto {

   private List<ShoppingNodeDto> nodes;

   public List<ShoppingNodeDto> getNodes() {
      return nodes;
   }

   public void setNodes(List<ShoppingNodeDto> nodes) {
      this.nodes = nodes;
   }
}
