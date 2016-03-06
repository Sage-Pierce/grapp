package com.wisegas.grapp.service.api;

import com.wisegas.grapp.service.dto.GrappStoreLayoutDTO;
import com.wisegas.grapp.service.dto.GrappStoreFeatureDTO;
import com.wisegas.grapp.service.dto.GrappStoreNodeDTO;
import com.wisegas.lang.GeoPoint;
import com.wisegas.lang.GeoPolygon;

public interface GrappStoreLayoutService {
   GrappStoreLayoutDTO findByID(String id);

   GrappStoreLayoutDTO updateOuterOutline(String id, GeoPolygon outerPolygon);

   GrappStoreLayoutDTO updateInnerOutline(String id, GeoPolygon innerPolygon);

   GrappStoreFeatureDTO addFeature(String id, GeoPolygon polygon);

   GrappStoreFeatureDTO reshapeFeature(String id, String featureID, GeoPolygon polygon);

   void removeFeature(String id, String featureID);

   GrappStoreNodeDTO addNode(String id, GeoPoint location);

   GrappStoreNodeDTO moveNode(String id, String nodeID, GeoPoint location);

   void removeNode(String id, String nodeID);
}
