package com.wisegas.grapp.storemanagement.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.grapp.storemanagement.domain.repository.NodeItemRepository;
import com.wisegas.grapp.storemanagement.domain.value.NodeItemId;
import com.wisegas.grapp.storemanagement.service.api.NodeItemService;
import com.wisegas.grapp.storemanagement.service.dto.NodeItemDto;
import com.wisegas.grapp.storemanagement.service_impl.factory.NodeItemDtoFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@Transactional
@ApplicationService
public class NodeItemServiceImpl implements NodeItemService {

   private final NodeItemRepository nodeItemRepository;

   @Inject
   public NodeItemServiceImpl(NodeItemRepository nodeItemRepository) {
      this.nodeItemRepository = nodeItemRepository;
   }

   @Override
   public NodeItemDto get(String id) {
      return NodeItemDtoFactory.createDto(nodeItemRepository.get(NodeItemId.fromString(id)));
   }
}
