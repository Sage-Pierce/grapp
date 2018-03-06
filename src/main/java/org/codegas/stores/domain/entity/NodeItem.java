package org.codegas.stores.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.codegas.common.domain.entity.SimpleEntity;
import org.codegas.stores.domain.value.Item;
import org.codegas.stores.domain.value.NodeItemId;

@Entity
public class NodeItem extends SimpleEntity<NodeItemId> {

    @EmbeddedId
    private NodeItemId id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    private Node node;

    private Item item;

    public NodeItem(Node node, Item item) {
        id = NodeItemId.generate();
        setNode(node);
        setItem(item);
    }

    protected NodeItem() {

    }

    @Override
    public NodeItemId getId() {
        return id;
    }

    public Node getNode() {
        return node;
    }

    public Item getItem() {
        return item;
    }

    private void setNode(Node node) {
        this.node = node;
    }

    private void setItem(Item item) {
        this.item = item;
    }
}
