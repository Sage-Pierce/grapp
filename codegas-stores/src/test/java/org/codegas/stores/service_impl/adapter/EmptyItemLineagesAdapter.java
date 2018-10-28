package org.codegas.stores.service_impl.adapter;

import java.util.Collections;
import java.util.List;

import org.codegas.stores.service.adapter.ItemLineagesAdapter;
import org.codegas.stores.service.dto.ItemLineageDto;

public class EmptyItemLineagesAdapter implements ItemLineagesAdapter {

    @Override
    public List<ItemLineageDto> getItemLineages() {
        return Collections.emptyList();
    }
}
