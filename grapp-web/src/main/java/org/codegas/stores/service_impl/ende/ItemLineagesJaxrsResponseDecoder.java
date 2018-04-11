package org.codegas.stores.service_impl.ende;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.core.Response;

import org.codegas.commons.ende.api.JaxrsResponseDecoder;
import org.codegas.stores.service.dto.ItemLineageDto;

public class ItemLineagesJaxrsResponseDecoder implements JaxrsResponseDecoder<List<ItemLineageDto>> {

    private static final ItemLineagesJsonValueDecoder DECODER = new ItemLineagesJsonValueDecoder();

    @Override
    public List<ItemLineageDto> decode(Response response) {
        return DECODER.decode(response.readEntity(InputStream.class));
    }
}
