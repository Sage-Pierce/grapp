package com.wisegas.grapp.service_impl.value;

import java.util.List;
import java.util.Objects;

public class NacsItem {

   private final NacsId id;
   private final String name;
   private final List<String> subItems;

   public NacsItem(NacsId id, String name, List<String> subItems) {
      this.id = id;
      this.name = name;
      this.subItems = subItems;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }

      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      NacsItem nacsItem = (NacsItem)o;
      return Objects.equals(this.id, nacsItem.id) &&
             Objects.equals(this.name, nacsItem.name) &&
             Objects.equals(this.subItems, nacsItem.subItems);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, name, subItems);
   }

   public NacsItemType getType() {
      if (id.isCategoryId()) {
         return NacsItemType.CATEGORY;
      }
      else {
         return id.isSubCategoryId() ? NacsItemType.SUB_CATEGORY : NacsItemType.ITEM;
      }
   }

   public NacsId getParentId() {
      return id.generateParentId();
   }

   public NacsId getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public List<String> getSubItems() {
      return subItems;
   }
}
