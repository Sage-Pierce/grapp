package com.wisegas.grapp.service.api;

import com.wisegas.grapp.itemmanagement.service.dto.GrappItemDTO;

import java.util.List;

public interface NacsItemImportService {
   List<GrappItemDTO> importCsvItems(String nacsItemCsvData);
}
