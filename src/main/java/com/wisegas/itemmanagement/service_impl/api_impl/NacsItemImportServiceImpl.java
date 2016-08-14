package com.wisegas.itemmanagement.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.itemmanagement.domain.entity.Item;
import com.wisegas.itemmanagement.domain.service.ItemImportService;
import com.wisegas.itemmanagement.domain.value.Code;
import com.wisegas.itemmanagement.domain.value.CodeType;
import com.wisegas.itemmanagement.service.api.NacsItemImportService;
import com.wisegas.itemmanagement.service.dto.ItemDto;
import com.wisegas.itemmanagement.service_impl.factory.ItemDtoFactory;
import com.wisegas.itemmanagement.service_impl.util.NacsItemCsvParser;
import com.wisegas.itemmanagement.service_impl.value.NacsId;
import com.wisegas.itemmanagement.service_impl.value.NacsItem;
import com.wisegas.itemmanagement.service_impl.value.NacsItemType;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Named
@Singleton
@Transactional
@ApplicationService
public class NacsItemImportServiceImpl implements NacsItemImportService {

   private final ItemImportService itemImportService;

   @Inject
   public NacsItemImportServiceImpl(ItemImportService itemImportService) {
      this.itemImportService = itemImportService;
   }

   @Override
   public List<ItemDto> importItems(String nacsItemCsvData) {
      Map<NacsItemType, List<NacsItem>> nacsItemsByType = NacsItemCsvParser.parse(nacsItemCsvData).stream().collect(groupingBy(NacsItem::getType));
      List<Item> categoryItems = importNacsItems(nacsItemsByType.getOrDefault(NacsItemType.CATEGORY, emptyList()), this::importNacsItemAsGeneralItem);
      List<Item> subCategoryItems = importNacsItems(nacsItemsByType.getOrDefault(NacsItemType.SUB_CATEGORY, emptyList()), this::importNacsItemAsSubItem);
      List<Item> subItemItems = importNacsItems(nacsItemsByType.getOrDefault(NacsItemType.ITEM, emptyList()), this::importNacsItemAsSubItem);
      return Stream.of(categoryItems, subCategoryItems, subItemItems)
                   .flatMap(Collection::stream)
                   .map(ItemDtoFactory::createDto)
                   .collect(toList());
   }

   private List<Item> importNacsItems(List<NacsItem> nacsItems, Function<NacsItem, Optional<Item>> nacsItemConverter) {
      return nacsItems.stream()
                      .map(nacsItemConverter)
                      .filter(Optional::isPresent)
                      .map(Optional::get)
                      .collect(toList());
   }

   private Optional<Item> importNacsItemAsGeneralItem(NacsItem nacsItem) {
      Code code = nacsIdToItemCode(nacsItem.getId());
      Optional<Item> createdGeneralItem = tryToImportItemAsGeneralItem(code, nacsItem.getName());
      createdGeneralItem.ifPresent(generalItem -> nacsItem.getSubItems().forEach(subItemName -> tryToImportItemAsSubItem(code, Code.random(), subItemName)));
      return createdGeneralItem;
   }

   private Optional<Item> importNacsItemAsSubItem(NacsItem nacsItem) {
      Code code = nacsIdToItemCode(nacsItem.getId());
      Optional<Item> createdSubItem = tryToImportItemAsSubItem(nacsIdToItemCode(nacsItem.getParentId()), code, nacsItem.getName());
      createdSubItem.ifPresent(subItem -> nacsItem.getSubItems().forEach(subItemName -> tryToImportItemAsSubItem(code, Code.random(), subItemName)));
      return createdSubItem;
   }

   private Code nacsIdToItemCode(NacsId nacsId) {
      return new Code(CodeType.NACS, nacsId.toString(CodeType.NACS.getValueFormat()));
   }

   private Optional<Item> tryToImportItemAsGeneralItem(Code code, String name) {
      try {
         return Optional.of(itemImportService.importGeneralItem(code, name));
      }
      catch (Exception e) {
         return Optional.empty();
      }
   }

   private Optional<Item> tryToImportItemAsSubItem(Code superCode, Code code, String name) {
      try {
         return Optional.of(itemImportService.importSubItem(superCode, code, name));
      }
      catch (Exception e) {
         return Optional.empty();
      }
   }
}
