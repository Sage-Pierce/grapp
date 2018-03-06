package org.codegas.stores.service.api;

import org.codegas.common.lang.spacial.GeoPoint;
import org.codegas.common.lang.spacial.GeoPolygon;
import org.codegas.common.lang.value.CodeName;
import org.codegas.stores.service.dto.FeatureDto;
import org.codegas.stores.service.dto.NodeDto;
import org.codegas.stores.service.dto.NodeItemDto;
import org.codegas.stores.service.dto.StoreLayoutDto;
import org.codegas.stores.service.dto.StoreLayoutUpdateDto;

public interface StoreLayoutService {

    StoreLayoutDto get(String id);

    StoreLayoutDto updateOuterOutline(String id, GeoPolygon polygon);

    StoreLayoutDto updateInnerOutline(String id, GeoPolygon polygon);

    FeatureDto addFeature(String id, GeoPolygon polygon);

    FeatureDto reshapeFeature(String id, String featureId, GeoPolygon polygon);

    StoreLayoutUpdateDto<NodeDto> addNode(String id, String type, GeoPoint location);

    NodeDto moveNode(String id, String nodeId, GeoPoint location);

    StoreLayoutUpdateDto<NodeItemDto> addNodeItem(String id, String nodeId, CodeName item);
}
