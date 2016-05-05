package com.wisegas.storemanagement.service_impl.adapter;

import com.wisegas.common.webservices.jaxrs.translation.ResponseTranslator;
import com.wisegas.storemanagement.service.adapter.ItemLineageAdapter;
import com.wisegas.storemanagement.service.dto.ItemLineageDto;
import com.wisegas.storemanagement.service_impl.translator.ItemLineagesResponseTranslator;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.client.WebTarget;
import java.util.List;

@Named
@Singleton
public class HttpItemManagementAdapter implements ItemLineageAdapter {
   private static final ResponseTranslator<List<ItemLineageDto>> translator = new ItemLineagesResponseTranslator();

   private final WebTarget itemLineageTarget;

   @Inject
   public HttpItemManagementAdapter(WebTarget itemManagementTarget) {
      itemLineageTarget = itemManagementTarget.path("items");
   }

   @Override
   public List<ItemLineageDto> getItemLineages() {
      return translator.translate(itemLineageTarget.request().get());
   }
}
