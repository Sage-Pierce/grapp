package com.wisegas.grapp.itemmanagement.service.api;

import com.wisegas.grapp.itemmanagement.service.dto.GrappItemDto;

import java.util.List;

public interface NacsItemImportService {
   List<GrappItemDto> importCsvItems(String nacsItemCsvData);
}
