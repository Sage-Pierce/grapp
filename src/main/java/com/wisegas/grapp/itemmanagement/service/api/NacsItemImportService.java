package com.wisegas.grapp.itemmanagement.service.api;

import com.wisegas.grapp.itemmanagement.service.dto.ItemDto;

import java.util.List;

public interface NacsItemImportService {
   List<ItemDto> importCsvItems(String nacsItemCsvData);
}
