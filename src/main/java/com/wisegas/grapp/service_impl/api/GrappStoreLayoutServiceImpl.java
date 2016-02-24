package com.wisegas.grapp.service_impl.api;

import com.wisegas.grapp.domain.entity.GrappStoreLayout;
import com.wisegas.grapp.domain.entity.GrappStoreLayoutFeature;
import com.wisegas.grapp.domain.repository.GrappStoreLayoutRepository;
import com.wisegas.grapp.domain.value.GrappStoreLayoutFeatureID;
import com.wisegas.grapp.domain.value.GrappStoreLayoutID;
import com.wisegas.grapp.service.api.GrappStoreLayoutService;
import com.wisegas.grapp.service.dto.GrappStoreLayoutDTO;
import com.wisegas.grapp.service.dto.GrappStoreLayoutFeatureDTO;
import com.wisegas.grapp.service_impl.factory.GrappStoreLayoutDTOFactory;
import com.wisegas.grapp.service_impl.factory.GrappStoreLayoutFeatureDTOFactory;
import com.wisegas.value.GeoPolygon;
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
   public GrappStoreLayoutFeatureDTO addFeature(String layoutID, GeoPolygon polygon) {
      GrappStoreLayout layout = grappStoreLayoutRepository.findByID(GrappStoreLayoutID.fromString(layoutID));
      GrappStoreLayoutFeature feature = layout.addFeature(polygon);
      return GrappStoreLayoutFeatureDTOFactory.createDTO(feature);
   }

   @Override
   public GrappStoreLayoutFeatureDTO reshapeFeature(String layoutID, String featureID, GeoPolygon polygon) {
      GrappStoreLayout layout = grappStoreLayoutRepository.findByID(GrappStoreLayoutID.fromString(layoutID));
      GrappStoreLayoutFeature feature = layout.reshapeFeature(GrappStoreLayoutFeatureID.fromString(featureID), polygon);
      return GrappStoreLayoutFeatureDTOFactory.createDTO(feature);
   }

   @Override
   public void removeFeature(String layoutID, String featureID) {
      GrappStoreLayout layout = grappStoreLayoutRepository.findByID(GrappStoreLayoutID.fromString(layoutID));
      layout.removeFeature(GrappStoreLayoutFeatureID.fromString(featureID));
   }
}