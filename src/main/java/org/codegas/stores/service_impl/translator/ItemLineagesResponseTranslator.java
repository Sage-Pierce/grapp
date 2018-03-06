package org.codegas.stores.service_impl.translator;

import java.util.List;

import javax.ws.rs.core.Response;

import org.codegas.commons.webservices.jaxrs.translation.ResponseTranslator;
import org.codegas.stores.service.dto.ItemLineageDto;

public class ItemLineagesResponseTranslator implements ResponseTranslator<List<ItemLineageDto>> {

    private static final ItemLineagesJsonTranslator jsonTranslator = new ItemLineagesJsonTranslator();

    @Override
    public List<ItemLineageDto> translate(Response response) {
        return jsonTranslator.translate(response.readEntity(String.class));
    }
}
