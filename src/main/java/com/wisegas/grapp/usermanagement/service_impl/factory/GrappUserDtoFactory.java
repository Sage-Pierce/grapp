package com.wisegas.grapp.usermanagement.service_impl.factory;

import com.wisegas.grapp.usermanagement.domain.entity.GrappUser;
import com.wisegas.grapp.usermanagement.service.dto.GrappUserDto;

public final class GrappUserDtoFactory {

   public static GrappUserDto createDto(GrappUser grappUser) {
      GrappUserDto grappUserDto = new GrappUserDto();
      grappUserDto.setId(grappUser.getId().toString());
      grappUserDto.setName(grappUser.getName());
      grappUserDto.setAvatar(grappUser.getAvatar());
      return grappUserDto;
   }

   private GrappUserDtoFactory() {

   }
}
