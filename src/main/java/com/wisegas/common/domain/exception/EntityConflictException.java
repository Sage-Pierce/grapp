package com.wisegas.common.domain.exception;

public class EntityConflictException extends RuntimeException {

   public EntityConflictException(String message) {
      super(message);
   }
}
