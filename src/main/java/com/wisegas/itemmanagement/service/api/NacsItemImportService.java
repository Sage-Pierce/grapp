package com.wisegas.itemmanagement.service.api;

import com.wisegas.itemmanagement.service.dto.ItemDto;

import java.util.List;

public interface NacsItemImportService {
   List<ItemDto> importCsvItems(String nacsItemCsvData);
}
