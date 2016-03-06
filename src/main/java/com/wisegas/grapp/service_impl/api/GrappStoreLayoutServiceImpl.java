package com.wisegas.grapp.service_impl.api;

import com.wisegas.grapp.domain.entity.GrappStoreFeature;
import com.wisegas.grapp.domain.entity.GrappStoreLayout;
import com.wisegas.grapp.domain.entity.GrappStoreNode;
import com.wisegas.grapp.domain.repository.GrappStoreLayoutRepository;
import com.wisegas.grapp.domain.value.GrappStoreFeatureID;
import com.wisegas.grapp.domain.value.GrappStoreLayoutID;
import com.wisegas.grapp.domain.value.GrappStoreNodeID;
import com.wisegas.grapp.service.api.GrappStoreLayoutService;
import com.wisegas.grapp.service.dto.GrappStoreLayoutDTO;
import com.wisegas.grapp.service.dto.GrappStoreFeatureDTO;
import com.wisegas.grapp.service.dto.GrappStoreNodeDTO;
import com.wisegas.grapp.service_impl.factory.GrappStoreLayoutDTOFactory;
import com.wisegas.grapp.service_impl.factory.GrappStoreFeatureDTOFactory;
import com.wisegas.grapp.service_impl.factory.GrappStoreNodeDTOFactory;
import com.wisegas.lang.GeoPoint;
import com.wisegas.lang.GeoPolygon;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@Transactional
public class GrappStoreLayoutServiceImpl implements GrappStoreLayoutService {

   private final GrappStoreLayoutRepository grappStoreLayoutRepository;

   @Inject
   public GrappStoreLayoutServiceImpl(GrappStoreLayoutRepository grappStoreLayoutRepository) {
      this.grappStoreLayoutRepository = grappStoreLayoutRepository;
   }

   @Override
   public GrappStoreLayoutDTO findByID(String id) {
      return GrappStoreLayoutDTOFactory.createDTO(grappStoreLayoutRepository.findByID(GrappStoreLayoutID.fromString(id)));
   }

   @Override
   public GrappStoreLayoutDTO updateOuterOutline(String id, GeoPolygon outerPolygon) {
      GrappStoreLayout layout = grappStoreLayoutRepository.findByID(GrappStoreLayoutID.fromString(id));
      layout.setOuterOutline(outerPolygon);
      return GrappStoreLayoutDTOFactory.createDTO(layout);
   }

   @Override
   public GrappStoreLayoutDTO updateInnerOutline(String id, GeoPolygon innerPolygon) {
      GrappStoreLayout layout = grappStoreLayoutRepository.findByID(GrappStoreLayoutID.fromString(id));
      layout.setInnerOutline(innerPolygon);
      return GrappStoreLayoutDTOFactory.createDTO(layout);
   }

   @Override
   public GrappStoreFeatureDTO addFeature(String id, GeoPolygon polygon) {
      GrappStoreLayout layout = grappStoreLayoutRepository.findByID(GrappStoreLayoutID.fromString(id));
      GrappStoreFeature feature = layout.addFeature(polygon);
      return GrappStoreFeatureDTOFactory.createDTO(feature);
   }

   @Override
   public GrappStoreFeatureDTO reshapeFeature(String id, String featureID, GeoPolygon polygon) {
      GrappStoreLayout layout = grappStoreLayoutRepository.findByID(GrappStoreLayoutID.fromString(id));
      GrappStoreFeature feature = layout.reshapeFeature(GrappStoreFeatureID.fromString(featureID), polygon);
      return GrappStoreFeatureDTOFactory.createDTO(feature);
   }

   @Override
   public void removeFeature(String id, String featureID) {
      GrappStoreLayout layout = grappStoreLayoutRepository.findByID(GrappStoreLayoutID.fromString(id));
      layout.removeFeature(GrappStoreFeatureID.fromString(featureID));
   }

   @Override
   public GrappStoreNodeDTO addNode(String id, GeoPoint location) {
      GrappStoreLayout layout = grappStoreLayoutRepository.findByID(GrappStoreLayoutID.fromString(id));
      GrappStoreNode grappStoreNode = layout.addNode(location);
      return GrappStoreNodeDTOFactory.createDTO(grappStoreNode);
   }

   @Override
   public GrappStoreNodeDTO moveNode(String id, String nodeID, GeoPoint location) {
      GrappStoreLayout layout = grappStoreLayoutRepository.findByID(GrappStoreLayoutID.fromString(id));
      GrappStoreNode grappStoreNode = layout.moveNode(GrappStoreNodeID.fromString(nodeID), location);
      return GrappStoreNodeDTOFactory.createDTO(grappStoreNode);
   }

   @Override
   public void removeNode(String id, String nodeID) {
      GrappStoreLayout layout = grappStoreLayoutRepository.findByID(GrappStoreLayoutID.fromString(id));
      layout.removeNode(GrappStoreNodeID.fromString(nodeID));
   }
}
