package com.wisegas.common.webservices.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * This class launches the web application in an embedded Jetty container. This is the entry point
 * to your application. The Java command that is used for launching should fire this main method.
 */
public class Main {

   public static void main(String[] args) throws Exception {
      // Port to run on should be an environment variable. Default to 8080 (later on) if not set.
      String port = System.getenv("PORT");

      // Not running WAR and 'root' is the context
      WebAppContext contextHandler = new WebAppContext(null, "/");
      // Parent loader priority is a class loader setting that Jetty accepts. By default Jetty will
      // behave like most web containers in that it will allow your application to replace
      // non-server libraries that are part of the container. Setting parent loader priority to
      // true changes this behavior. Read more: wiki.eclipse.org/Jetty/Reference/Jetty_Classloading
      contextHandler.setParentLoaderPriority(true);
      contextHandler.setResourceBase("src/main/webapp/");
      contextHandler.setDescriptor(contextHandler.getResourceBase() + "WEB-INF/web.xml");

      Server server = new Server(port == null || port.isEmpty() ? 8080 : Integer.valueOf(port));
      server.setHandler(contextHandler);
      server.start();
      server.join();
   }
}
