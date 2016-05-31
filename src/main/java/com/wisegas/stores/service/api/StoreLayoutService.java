package com.wisegas.stores.service.api;

import com.wisegas.common.lang.spacial.GeoPoint;
import com.wisegas.common.lang.spacial.GeoPolygon;
import com.wisegas.common.lang.value.CodeName;
import com.wisegas.stores.service.dto.*;

public interface StoreLayoutService {
   StoreLayoutDto get(String id);

   StoreLayoutDto updateOuterOutline(String id, GeoPolygon outerPolygon);

   StoreLayoutDto updateInnerOutline(String id, GeoPolygon innerPolygon);

   FeatureDto addFeature(String id, GeoPolygon polygon);

   FeatureDto reshapeFeature(String id, String featureId, GeoPolygon polygon);

   StoreLayoutUpdateDto<NodeDto> addNode(String id, String type, GeoPoint location);

   NodeDto moveNode(String id, String nodeId, GeoPoint location);

   StoreLayoutUpdateDto<NodeItemDto> addNodeItem(String id, String nodeId, CodeName item);
}
