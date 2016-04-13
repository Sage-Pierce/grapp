package com.wisegas.grapp.storemanagement.service.api;

import com.wisegas.grapp.storemanagement.service.dto.NodeDto;

public interface NodeService {
   NodeDto get(String id);

   NodeDto update(String id, String name);
}
