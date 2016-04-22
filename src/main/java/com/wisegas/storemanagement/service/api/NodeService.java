package com.wisegas.storemanagement.service.api;

import com.wisegas.storemanagement.service.dto.NodeDto;

public interface NodeService {
   NodeDto get(String id);

   NodeDto update(String id, String name);

   void delete(String id);
}
