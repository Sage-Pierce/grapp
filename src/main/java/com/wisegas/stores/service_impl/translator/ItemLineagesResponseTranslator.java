package com.wisegas.stores.service_impl.translator;

import com.wisegas.common.webservices.jaxrs.translation.ResponseTranslator;
import com.wisegas.stores.service.dto.ItemLineageDto;

import javax.ws.rs.core.Response;
import java.util.List;

public class ItemLineagesResponseTranslator implements ResponseTranslator<List<ItemLineageDto>> {
   private static final ItemLineagesJsonTranslator jsonTranslator = new ItemLineagesJsonTranslator();

   @Override
   public List<ItemLineageDto> translate(Response response) {
      return jsonTranslator.translate(response.readEntity(String.class));
   }
}
