package com.wisegas.grapp.storemanagement.service.api;

import com.wisegas.common.lang.value.CodeName;
import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.lang.value.GeoPolygon;
import com.wisegas.grapp.storemanagement.service.dto.*;

public interface LayoutService {
   LayoutDto get(String id);

   LayoutDto updateOuterOutline(String id, GeoPolygon outerPolygon);

   LayoutDto updateInnerOutline(String id, GeoPolygon innerPolygon);

   FeatureDto addFeature(String id, GeoPolygon polygon);

   FeatureDto reshapeFeature(String id, String featureID, GeoPolygon polygon);

   void removeFeature(String id, String featureID);

   LayoutUpdateDto<NodeDto> addNode(String id, String type, GeoPoint location);

   NodeDto moveNode(String id, String nodeID, GeoPoint location);

   void removeNode(String id, String nodeID);

   LayoutUpdateDto<NodeItemDto> addNodeItem(String id, String nodeID, CodeName item);
}
