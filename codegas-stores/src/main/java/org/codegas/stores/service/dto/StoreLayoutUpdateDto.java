package org.codegas.stores.service.dto;

import java.util.List;

public class StoreLayoutUpdateDto<T> {

    private T target;

    private List<NodeDto> affectedNodes;

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    public List<NodeDto> getAffectedNodes() {
        return affectedNodes;
    }

    public void setAffectedNodes(List<NodeDto> affectedNodes) {
        this.affectedNodes = affectedNodes;
    }
}
