package com.wisegas.grapp.storemanagement.service.api;

import com.wisegas.common.lang.value.CodeName;
import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.lang.value.GeoPolygon;
import com.wisegas.grapp.storemanagement.service.dto.*;

public interface GrappStoreLayoutService {
   GrappStoreLayoutDTO get(String id);

   GrappStoreLayoutDTO updateOuterOutline(String id, GeoPolygon outerPolygon);

   GrappStoreLayoutDTO updateInnerOutline(String id, GeoPolygon innerPolygon);

   GrappStoreFeatureDTO addFeature(String id, GeoPolygon polygon);

   GrappStoreFeatureDTO reshapeFeature(String id, String featureID, GeoPolygon polygon);

   void removeFeature(String id, String featureID);

   GrappStoreLayoutUpdateResultDTO<GrappStoreNodeDTO> addNode(String id, String type, GeoPoint location);

   GrappStoreNodeDTO moveNode(String id, String nodeID, GeoPoint location);

   void removeNode(String id, String nodeID);

   GrappStoreLayoutUpdateResultDTO<GrappStoreNodeItemDTO> addNodeItem(String id, String nodeID, CodeName item);
}
