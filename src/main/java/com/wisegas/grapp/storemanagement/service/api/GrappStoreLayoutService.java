package com.wisegas.grapp.storemanagement.service.api;

import com.wisegas.common.lang.value.CodeName;
import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.lang.value.GeoPolygon;
import com.wisegas.grapp.storemanagement.service.dto.*;

public interface GrappStoreLayoutService {
   GrappStoreLayoutDto get(String id);

   GrappStoreLayoutDto updateOuterOutline(String id, GeoPolygon outerPolygon);

   GrappStoreLayoutDto updateInnerOutline(String id, GeoPolygon innerPolygon);

   GrappStoreFeatureDto addFeature(String id, GeoPolygon polygon);

   GrappStoreFeatureDto reshapeFeature(String id, String featureID, GeoPolygon polygon);

   void removeFeature(String id, String featureID);

   GrappStoreLayoutUpdateResultDto<GrappStoreNodeDto> addNode(String id, String type, GeoPoint location);

   GrappStoreNodeDto moveNode(String id, String nodeID, GeoPoint location);

   void removeNode(String id, String nodeID);

   GrappStoreLayoutUpdateResultDto<GrappStoreNodeItemDto> addNodeItem(String id, String nodeID, CodeName item);
}
