FROM openjdk:8-jre-alpine

# Copy dependency JARs
COPY grapp-web/target/dependency /dependency

# Copy compiled classes
COPY grapp-web/target/classes /classes

# Copy webapp
COPY grapp-web/src/main/webapp /webapp

# Run Jetty Application
CMD ["/usr/bin/java", "-cp", "/dependency/*:/classes", "-Dspring.profiles.active=production", "org.codegas.webservice.jetty.Main", "-wad", "/webapp"]