package com.wisegas.grapp.usermanagement.service_impl.factory;

import com.wisegas.grapp.usermanagement.domain.entity.GrappUser;
import com.wisegas.grapp.usermanagement.service.dto.GrappUserDTO;

public final class GrappUserDTOFactory {

   public static GrappUserDTO createDTO(GrappUser grappUser) {
      GrappUserDTO grappUserDTO = new GrappUserDTO();
      grappUserDTO.setId(grappUser.getId().toString());
      grappUserDTO.setName(grappUser.getName());
      grappUserDTO.setAvatar(grappUser.getAvatar());
      return grappUserDTO;
   }

   private GrappUserDTOFactory() {

   }
}
