package org.codegas.stores.service_impl.adapter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.client.WebTarget;

import org.codegas.common.webservices.jaxrs.translation.ResponseTranslator;
import org.codegas.stores.service.adapter.ItemLineagesAdapter;
import org.codegas.stores.service.dto.ItemLineageDto;
import org.codegas.stores.service_impl.translator.ItemLineagesResponseTranslator;

@Named
@Singleton
public class HttpItemManagementAdapter implements ItemLineagesAdapter {

    private static final ResponseTranslator<List<ItemLineageDto>> translator = new ItemLineagesResponseTranslator();

    private final WebTarget itemManagementTarget;

    @Inject
    public HttpItemManagementAdapter(WebTarget itemManagementTarget) {
        this.itemManagementTarget = itemManagementTarget;
    }

    @Override
    public List<ItemLineageDto> getItemLineages() {
        return translator.translate(itemManagementTarget.path("items").request().get());
    }
}
