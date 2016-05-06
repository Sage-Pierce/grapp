package com.wisegas.storemanagement.service.dto;

import java.util.List;

public class StoreLayoutDto extends LayoutDto {

   private List<NodeDto> nodes;

   public List<NodeDto> getNodes() {
      return nodes;
   }

   public void setNodes(List<NodeDto> nodes) {
      this.nodes = nodes;
   }
}
