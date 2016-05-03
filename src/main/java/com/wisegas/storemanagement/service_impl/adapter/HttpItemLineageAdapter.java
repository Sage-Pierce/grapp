package com.wisegas.storemanagement.service_impl.adapter;

import com.wisegas.common.webservices.jaxrs.mediatype.HalJson;
import com.wisegas.storemanagement.service.adapter.ItemLineageAdapter;
import com.wisegas.storemanagement.service.dto.ItemLineageDto;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import java.util.List;

@Named
@Singleton
public class HttpItemLineageAdapter implements ItemLineageAdapter {



   @Override
   public List<ItemLineageDto> getItemLineages() {
      Client storeManagementClient = ClientBuilder.newClient();
      WebTarget storeItemManagementTarget = storeManagementClient.target("http://localhost:8080/rest");
      WebTarget itemLineageTarget = storeItemManagementTarget.path("items");
      Invocation.Builder invocationBuilder = itemLineageTarget.request(HalJson.getInstance());
      return null;
   }
}
