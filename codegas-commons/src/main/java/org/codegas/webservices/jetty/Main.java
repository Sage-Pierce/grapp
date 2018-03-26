package org.codegas.webservices.jetty;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * This class launches the web application in an embedded Jetty container. This is the entry point
 * to your application. The Java command that is used for launching should fire this main method.
 */
public class Main {

    private static final String WEB_APP_DIRECTORY_OPT = "wad";

    private static final String PORT_OPT = "p";

    private static final Options OPTIONS = new Options()
        .addOption(Option.builder(WEB_APP_DIRECTORY_OPT).argName("webAppDirectory").hasArg()
            .desc("Base directory of web app (containing WEB-INF directory)").build())
        .addOption(Option.builder(PORT_OPT).argName("port").hasArg()
            .desc("Port on which to run Application").build());

    public static void main(String[] args) throws Exception {
        CommandLine commandLine = new DefaultParser().parse(OPTIONS, args);

        // Point at web app directory as the root context
        WebAppContext contextHandler = new WebAppContext(commandLine.getOptionValue(WEB_APP_DIRECTORY_OPT, "src/main/webapp"), "/");
        // Parent loader priority is a class loader setting that Jetty accepts. By default Jetty will
        // behave like most web containers in that it will allow your application to replace
        // non-server libraries that are part of the container. Setting parent loader priority to
        // true changes this behavior. Read more: wiki.eclipse.org/Jetty/Reference/Jetty_Classloading
        contextHandler.setParentLoaderPriority(true);
        contextHandler.setDescriptor("WEB-INF/web.xml");

        // Port to run on should be an argument or environment variable. Default to 8080 if not set.
        String port = commandLine.getOptionValue(PORT_OPT, System.getenv().getOrDefault("PORT", "8080"));

        Server server = new Server(Integer.valueOf(port));
        server.setHandler(contextHandler);
        server.start();
        server.join();
    }
}
