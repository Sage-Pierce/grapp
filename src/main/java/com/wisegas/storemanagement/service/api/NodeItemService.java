package com.wisegas.storemanagement.service.api;

import com.wisegas.storemanagement.service.dto.NodeItemDto;

public interface NodeItemService {
   NodeItemDto get(String id);

   void delete(String id);
}
