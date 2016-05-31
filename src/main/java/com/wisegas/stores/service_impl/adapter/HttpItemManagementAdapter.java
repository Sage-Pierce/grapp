package com.wisegas.stores.service_impl.adapter;

import com.wisegas.common.webservices.jaxrs.translation.ResponseTranslator;
import com.wisegas.stores.service.adapter.ItemLineagesAdapter;
import com.wisegas.stores.service.dto.ItemLineageDto;
import com.wisegas.stores.service_impl.translator.ItemLineagesResponseTranslator;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.client.WebTarget;
import java.util.List;

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
