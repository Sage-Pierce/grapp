package org.codegas.stores.service_impl.factory;

import java.util.stream.Collectors;

import org.codegas.stores.domain.entity.StoreLayout;
import org.codegas.stores.service.dto.StoreLayoutDto;

public final class StoreLayoutDtoFactory {

    private StoreLayoutDtoFactory() {

    }

    public static StoreLayoutDto createDto(StoreLayout storeLayout) {
        StoreLayoutDto storeLayoutDto = new StoreLayoutDto();
        storeLayoutDto.setId(storeLayout.getId().toString());
        storeLayoutDto.setOuterOutline(storeLayout.getOuterOutline());
        storeLayoutDto.setInnerOutline(storeLayout.getInnerOutline());
        storeLayoutDto.setFeatures(storeLayout.getFeatures().stream().map(FeatureDtoFactory::createDto).collect(Collectors.toList()));
        storeLayoutDto.setNodes(storeLayout.getNodes().stream().map(NodeDtoFactory::createDto).collect(Collectors.toList()));
        return storeLayoutDto;
    }
}
