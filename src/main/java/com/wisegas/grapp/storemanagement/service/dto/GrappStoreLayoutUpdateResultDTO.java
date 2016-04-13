package com.wisegas.grapp.storemanagement.service.dto;

import java.util.List;

public class GrappStoreLayoutUpdateResultDTO<T> {

   private T target;
   private List<GrappStoreNodeDTOO> affectedNodes;

   public T getTarget() {
      return target;
   }

   public void setTarget(T target) {
      this.target = target;
   }

   public List<GrappStoreNodeDTOO> getAffectedNodes() {
      return affectedNodes;
   }

   public void setAffectedNodes(List<GrappStoreNodeDTOO> affectedNodes) {
      this.affectedNodes = affectedNodes;
   }
}
