package com.wisegas.stores.service.dto;

import java.util.List;

public class NodeDto extends AbstractNodeDto {

   private List<NodeItemDto> items;

   public List<NodeItemDto> getItems() {
      return items;
   }

   public void setItems(List<NodeItemDto> items) {
      this.items = items;
   }
}
