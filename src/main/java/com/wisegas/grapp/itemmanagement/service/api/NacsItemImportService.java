package com.wisegas.grapp.itemmanagement.service.api;

import com.wisegas.grapp.itemmanagement.service.dto.GrappItemDTOO;

import java.util.List;

public interface NacsItemImportService {
   List<GrappItemDTOO> importCsvItems(String nacsItemCsvData);
}
