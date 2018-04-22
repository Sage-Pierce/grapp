package org.codegas.stores.service_impl.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.codegas.stores.domain.entity.StoreLayout;
import org.codegas.stores.service.dto.ShoppingLayoutDto;
import org.codegas.stores.service.dto.ShoppingNodeDto;

public final class ShoppingLayoutDtoFactory {

    private ShoppingLayoutDtoFactory() {

    }

    public static ShoppingLayoutDto createDto(StoreLayout storeLayout, List<ShoppingNodeDto> nodes) {
        ShoppingLayoutDto shoppingLayoutDto = new ShoppingLayoutDto();
        shoppingLayoutDto.setId(storeLayout.getId().toString());
        shoppingLayoutDto.setOuterOutline(storeLayout.getOuterOutline());
        shoppingLayoutDto.setInnerOutline(storeLayout.getInnerOutline());
        shoppingLayoutDto.setFeatures(storeLayout.getFeatures().stream().map(FeatureDtoFactory::createDto).collect(Collectors.toList()));
        shoppingLayoutDto.setNodes(nodes);
        return shoppingLayoutDto;
    }
}
