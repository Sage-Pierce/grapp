package com.wisegas.grapp.service_impl.factory;

import com.wisegas.grapp.domain.entity.GrappUser;
import com.wisegas.grapp.service.dto.GrappUserDTO;

public final class GrappUserDTOFactory {

   public static GrappUserDTO createDTO(GrappUser grappUser) {
      GrappUserDTO grappUserDTO = new GrappUserDTO();
      grappUserDTO.setId(grappUser.getId().toString());
      grappUserDTO.setName(grappUser.getName());
      grappUserDTO.setEmail(grappUser.getEmail());
      grappUserDTO.setAvatar(grappUser.getAvatar());
      return grappUserDTO;
   }

   private GrappUserDTOFactory() {

   }
}
