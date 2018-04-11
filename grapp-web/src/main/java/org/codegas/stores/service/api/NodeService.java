package org.codegas.stores.service.api;

import org.codegas.stores.service.dto.NodeDto;

public interface NodeService {

    NodeDto get(String id);

    NodeDto update(String id, String name);

    void delete(String id);
}
