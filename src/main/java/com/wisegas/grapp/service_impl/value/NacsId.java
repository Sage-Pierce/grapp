package com.wisegas.grapp.service_impl.value;

import com.wisegas.grapp.domain.value.GrappItemCodeType;

import java.util.Objects;

public class NacsId {

   private final Integer categoryId;
   private final Integer subCategoryId;
   private final Integer itemId;

   public NacsId(Integer categoryId, Integer subCategoryId, Integer itemId) {
      this.categoryId = categoryId;
      this.subCategoryId = subCategoryId;
      this.itemId = itemId;
   }

   public NacsId generateParentId() {
      if (isCategoryId()) {
         throw new UnsupportedOperationException("Can't get Parent ID of NACS Category Item");
      }
      else {
         return isSubCategoryId() ? new NacsId(categoryId, 0, 0) : new NacsId(categoryId, subCategoryId, 0);
      }
   }

   public boolean isCategoryId() {
      return !isSubCategoryId() && !isItemId();
   }

   public boolean isSubCategoryId() {
      return !isItemId() && !Objects.equals(subCategoryId, 0);
   }

   public boolean isItemId() {
      return !Objects.equals(itemId, 0);
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }

      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      NacsId nacsId = (NacsId)o;
      return Objects.equals(this.categoryId, nacsId.categoryId) &&
             Objects.equals(this.subCategoryId, nacsId.subCategoryId) &&
             Objects.equals(this.itemId, nacsId.itemId);
   }

   @Override
   public int hashCode() {
      return Objects.hash(categoryId, subCategoryId, itemId);
   }

   @Override
   public String toString() {
      return GrappItemCodeType.NACS.formatCodeValue(categoryId, subCategoryId, itemId);
   }
}
