package com.wisegas.stores.service.api;

import com.wisegas.stores.service.dto.NodeDto;

public interface NodeService {
   NodeDto get(String id);

   NodeDto update(String id, String name);

   void delete(String id);
}
