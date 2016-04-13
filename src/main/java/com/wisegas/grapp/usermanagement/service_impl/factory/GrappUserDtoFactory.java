package com.wisegas.grapp.usermanagement.service_impl.factory;

import com.wisegas.grapp.usermanagement.domain.entity.GrappUser;
import com.wisegas.grapp.usermanagement.service.dto.GrappUserDto;

public final class GrappUserDtoFactory {

   public static GrappUserDto createDTO(GrappUser grappUser) {
      GrappUserDto grappUserDTO = new GrappUserDto();
      grappUserDTO.setId(grappUser.getId().toString());
      grappUserDTO.setName(grappUser.getName());
      grappUserDTO.setAvatar(grappUser.getAvatar());
      return grappUserDTO;
   }

   private GrappUserDtoFactory() {

   }
}
