# Grapp

This project is a Proof of Concept for a Java-Javascript application that manages shopping lists, shopping items, and store layouts, all combining to support an app that takes shopping lists and plans out efficient shopping routes.

### Deployment

This project is organized to a broad standard of project structure such as to support a wide array of deployment options

##### Tomcat

For Apache Tomcat configuration, change the packaging of `grapp-web` to `war` and add a build `finalName` of `ROOT` which will cause Tomcat to map the application to the root ("/") path

##### Jetty

Deploying with a Jetty embedded server is done via `org.codegas.webservice.jetty.Main` which allows specifying the webapp directory and port

### Docker

This application is also Dockerized via copying dependency JARs and compiled classes in to a Docker image and executing with the Jetty embedded server

##### Building Docker Image

`docker build -t org.codegas/grapp:latest .`