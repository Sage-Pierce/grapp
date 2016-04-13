package com.wisegas.grapp.storemanagement.service.dto;

import java.util.List;

public class GrappStoreLayoutUpdateResultDTOO<T> {

   private T target;
   private List<GrappStoreNodeDto> affectedNodes;

   public T getTarget() {
      return target;
   }

   public void setTarget(T target) {
      this.target = target;
   }

   public List<GrappStoreNodeDto> getAffectedNodes() {
      return affectedNodes;
   }

   public void setAffectedNodes(List<GrappStoreNodeDto> affectedNodes) {
      this.affectedNodes = affectedNodes;
   }
}
