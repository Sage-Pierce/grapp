package org.codegas.itemmanagement.service.api;

import java.util.List;

import org.codegas.itemmanagement.service.dto.ItemDto;

public interface NacsItemImportService {

    List<ItemDto> importItems(String csvData);
}
