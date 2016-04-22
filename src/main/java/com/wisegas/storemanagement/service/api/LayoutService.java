package com.wisegas.storemanagement.service.api;

import com.wisegas.common.lang.value.CodeName;
import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.lang.value.GeoPolygon;
import com.wisegas.storemanagement.service.dto.*;

public interface LayoutService {
   LayoutDto get(String id);

   LayoutDto updateOuterOutline(String id, GeoPolygon outerPolygon);

   LayoutDto updateInnerOutline(String id, GeoPolygon innerPolygon);

   FeatureDto addFeature(String id, GeoPolygon polygon);

   FeatureDto reshapeFeature(String id, String featureId, GeoPolygon polygon);

   LayoutUpdateDto<NodeDto> addNode(String id, String type, GeoPoint location);

   NodeDto moveNode(String id, String nodeId, GeoPoint location);

   LayoutUpdateDto<NodeItemDto> addNodeItem(String id, String nodeId, CodeName item);
}
