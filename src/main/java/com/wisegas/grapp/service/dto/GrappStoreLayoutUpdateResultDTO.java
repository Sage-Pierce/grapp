package com.wisegas.grapp.service.dto;

import java.util.List;

public class GrappStoreLayoutUpdateResultDTO<T> {

   private T target;
   private List<GrappStoreNodeDTO> affectedNodes;

   public T getTarget() {
      return target;
   }

   public void setTarget(T target) {
      this.target = target;
   }

   public List<GrappStoreNodeDTO> getAffectedNodes() {
      return affectedNodes;
   }

   public void setAffectedNodes(List<GrappStoreNodeDTO> affectedNodes) {
      this.affectedNodes = affectedNodes;
   }
}
