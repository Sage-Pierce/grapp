package com.wisegas.grapp.usermanagement.service_impl.factory;

import com.wisegas.grapp.usermanagement.domain.entity.GrappUser;
import com.wisegas.grapp.usermanagement.service.dto.GrappUserDTOO;

public final class GrappUserDTOFactory {

   public static GrappUserDTOO createDTO(GrappUser grappUser) {
      GrappUserDTOO grappUserDTO = new GrappUserDTOO();
      grappUserDTO.setId(grappUser.getId().toString());
      grappUserDTO.setName(grappUser.getName());
      grappUserDTO.setAvatar(grappUser.getAvatar());
      return grappUserDTO;
   }

   private GrappUserDTOFactory() {

   }
}
