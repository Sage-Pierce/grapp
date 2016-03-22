package com.wisegas.common.webserver.hal.api;

public class HALLink {

   private String rel;
   private String href;

   public HALLink(String rel, String href) {
      this.rel = rel;
      this.href = href;
   }

   public String getRel() {
      return rel;
   }

   public String getHref() {
      return href;
   }
}
