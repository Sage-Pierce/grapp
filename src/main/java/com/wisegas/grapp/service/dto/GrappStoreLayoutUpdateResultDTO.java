package com.wisegas.grapp.service.dto;

import java.util.List;

public class GrappStoreLayoutUpdateResultDTO<T> {

   private T target;
   private List<GrappStoreNodeDTO> affectedNode;

   public T getTarget() {
      return target;
   }

   public void setTarget(T target) {
      this.target = target;
   }

   public List<GrappStoreNodeDTO> getAffectedNodeDTOs() {
      return affectedNode;
   }

   public void setAffectedNodeDTOs(List<GrappStoreNodeDTO> affectedNodeDTOs) {
      this.affectedNode = affectedNodeDTOs;
   }
}
