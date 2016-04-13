package com.wisegas.grapp.storemanagement.service.api;

import com.wisegas.common.lang.value.CodeName;
import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.lang.value.GeoPolygon;
import com.wisegas.grapp.storemanagement.service.dto.*;

public interface GrappStoreLayoutService {
   GrappStoreLayoutDTOO get(String id);

   GrappStoreLayoutDTOO updateOuterOutline(String id, GeoPolygon outerPolygon);

   GrappStoreLayoutDTOO updateInnerOutline(String id, GeoPolygon innerPolygon);

   GrappStoreFeatureDTOO addFeature(String id, GeoPolygon polygon);

   GrappStoreFeatureDTOO reshapeFeature(String id, String featureID, GeoPolygon polygon);

   void removeFeature(String id, String featureID);

   GrappStoreLayoutUpdateResultDTO<GrappStoreNodeDTOO> addNode(String id, String type, GeoPoint location);

   GrappStoreNodeDTOO moveNode(String id, String nodeID, GeoPoint location);

   void removeNode(String id, String nodeID);

   GrappStoreLayoutUpdateResultDTO<GrappStoreNodeItemDTOO> addNodeItem(String id, String nodeID, CodeName item);
}
