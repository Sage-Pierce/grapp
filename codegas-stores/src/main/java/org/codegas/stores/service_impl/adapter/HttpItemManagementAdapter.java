package org.codegas.stores.service_impl.adapter;

import java.util.List;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.codegas.commons.lang.ende.Decoder;
import org.codegas.stores.service.adapter.ItemLineagesAdapter;
import org.codegas.stores.service.dto.ItemLineageDto;
import org.codegas.stores.service_impl.ende.ItemLineagesJaxrsResponseDecoder;

public class HttpItemManagementAdapter implements ItemLineagesAdapter {

    private static final Decoder<Response, List<ItemLineageDto>> DECODER = new ItemLineagesJaxrsResponseDecoder();

    private final WebTarget itemManagementTarget;

    public HttpItemManagementAdapter(WebTarget itemManagementTarget) {
        this.itemManagementTarget = itemManagementTarget;
    }

    @Override
    public List<ItemLineageDto> getItemLineages() {
        return DECODER.decode(itemManagementTarget.path("items").request().get());
    }
}
