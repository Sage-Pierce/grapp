package com.wisegas.stores.service.api;

import com.wisegas.stores.service.dto.NodeItemDto;

public interface NodeItemService {
   NodeItemDto get(String id);

   void delete(String id);
}
