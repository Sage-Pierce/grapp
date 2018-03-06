package org.codegas.stores.service.api;

import org.codegas.stores.service.dto.NodeItemDto;

public interface NodeItemService {

    NodeItemDto get(String id);

    void delete(String id);
}
