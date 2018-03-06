package org.codegas.pathgeneration.service_impl.factory;

import org.codegas.pathgeneration.domain.value.Path;
import org.codegas.pathgeneration.service.dto.PathDto;

public final class PathDtoFactory {

    private PathDtoFactory() {

    }

    public static PathDto createDto(Path path) {
        PathDto pathDto = new PathDto();
        pathDto.setPoints(path.getPoints());
        return pathDto;
    }
}
