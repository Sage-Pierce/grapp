package com.wisegas.storemanagement.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.storemanagement.domain.entity.Node;
import com.wisegas.storemanagement.domain.repository.NodeRepository;
import com.wisegas.storemanagement.domain.value.NodeId;
import com.wisegas.storemanagement.service.api.NodeService;
import com.wisegas.storemanagement.service.dto.NodeDto;
import com.wisegas.storemanagement.service_impl.factory.NodeDtoFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@Transactional
@ApplicationService
public class NodeServiceImpl implements NodeService {

   private final NodeRepository nodeRepository;

   @Inject
   public NodeServiceImpl(NodeRepository nodeRepository) {
      this.nodeRepository = nodeRepository;
   }

   @Override
   public NodeDto get(String id) {
      return NodeDtoFactory.createDto(nodeRepository.get(NodeId.fromString(id)));
   }

   @Override
   public NodeDto update(String id, String name) {
      Node node = nodeRepository.get(NodeId.fromString(id));
      node.setName(name);
      return NodeDtoFactory.createDto(node);
   }
}