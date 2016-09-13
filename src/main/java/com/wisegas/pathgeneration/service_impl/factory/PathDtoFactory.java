package com.wisegas.pathgeneration.service_impl.factory;

import com.wisegas.pathgeneration.domain.value.Path;
import com.wisegas.pathgeneration.service.dto.PathDto;

public final class PathDtoFactory {

   private PathDtoFactory() {

   }

   public static PathDto createDto(Path path) {
      PathDto pathDto = new PathDto();
      pathDto.setPoints(path.getPoints());
      return pathDto;
   }
}
