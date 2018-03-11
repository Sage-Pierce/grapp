package org.codegas.stores.service_impl.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.codegas.stores.domain.entity.StoreLayout;
import org.codegas.stores.domain.value.NodeId;
import org.codegas.stores.service.dto.StoreLayoutUpdateDto;

public final class StoreLayoutUpdateDtoFactory {

    private StoreLayoutUpdateDtoFactory() {

    }

    public static <T> StoreLayoutUpdateDto<T> createDto(StoreLayout storeLayout, T targetDto, List<String> affectedNodeIds) {
        StoreLayoutUpdateDto<T> resultDto = new StoreLayoutUpdateDto<>();
        resultDto.setTarget(targetDto);
        resultDto.setAffectedNodes(affectedNodeIds.stream().map(NodeId::fromString).map(storeLayout::getNode).map(NodeDtoFactory::createDto).collect(Collectors.toList()));
        return resultDto;
    }
}
