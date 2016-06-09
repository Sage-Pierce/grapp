package com.wisegas.stores.domain.entity;

import com.wisegas.common.domain.entity.SimpleEntity;
import com.wisegas.stores.domain.value.Item;
import com.wisegas.stores.domain.value.NodeItemId;

import javax.persistence.*;

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
