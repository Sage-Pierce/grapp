package org.codegas.stores.domain.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import org.codegas.commons.domain.event.DomainEventPublisher;
import org.codegas.commons.lang.spacial.GeoPoint;
import org.codegas.commons.jpa.converter.GeoPointStringConverter;
import org.codegas.commons.domain.entity.NamedEntity;
import org.codegas.stores.domain.event.NodeModifiedEvent;
import org.codegas.stores.domain.value.Item;
import org.codegas.stores.domain.value.NodeId;
import org.codegas.stores.domain.value.NodeType;

@Entity
public class Node extends NamedEntity<NodeId> {

    @EmbeddedId
    private NodeId id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    private StoreLayout storeLayout;

    @Column(length = 63)
    @Convert(converter = GeoPointStringConverter.class)
    private GeoPoint location;

    @Enumerated(EnumType.STRING)
    private NodeType type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "node", orphanRemoval = true)
    @MapKey(name = "item")
    private Map<Item, NodeItem> items = new HashMap<>();

    public Node(StoreLayout storeLayout, String name, NodeType type, GeoPoint location) {
        id = NodeId.generate();
        setStoreLayout(storeLayout);
        setName(name);
        setType(type);
        setLocation(location);
    }

    protected Node() {

    }

    @Override
    public NodeId getId() {
        return id;
    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        if (this.type != type) {
            this.type = type;
            DomainEventPublisher.instance().publish(new NodeModifiedEvent(id.toString()));
        }
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public boolean containsItem(Item item) {
        return items.containsKey(item);
    }

    public Collection<NodeItem> getItems() {
        return items.values();
    }

    public NodeItem addItem(Item item) {
        return items.computeIfAbsent(item, code -> new NodeItem(this, item));
    }

    public void removeItem(Item item) {
        if (items.containsKey(item)) {
            items.remove(item);
            DomainEventPublisher.instance().publish(new NodeModifiedEvent(id.toString()));
        }
    }

    private void setStoreLayout(StoreLayout storeLayout) {
        this.storeLayout = storeLayout;
    }
}
