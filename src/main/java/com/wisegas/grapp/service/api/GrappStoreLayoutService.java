package com.wisegas.grapp.service.api;

import com.wisegas.grapp.service.dto.GrappStoreLayoutDTO;
import com.wisegas.grapp.service.dto.GrappStoreLayoutFeatureDTO;
import com.wisegas.lang.GeoPolygon;

public interface GrappStoreLayoutService {
   GrappStoreLayoutDTO findByID(String id);

   GrappStoreLayoutDTO updateOuterOutline(String id, GeoPolygon outerPolygon);

   GrappStoreLayoutDTO updateInnerOutline(String id, GeoPolygon innerPolygon);

   GrappStoreLayoutFeatureDTO addFeature(String layoutID, GeoPolygon polygon);

   GrappStoreLayoutFeatureDTO reshapeFeature(String layoutID, String featureID, GeoPolygon polygon);

   void removeFeature(String layoutID, String featureID);
}
