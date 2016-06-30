# Personalize Weather Application
This is a sample application to check the real-time weather information for different cities

## Frameworks

### Front-end
Twitter Bootstrap, AngularJS

### Back-end
Spring Framework (4.2.6.RELEASE), Spring Data JPA (1.9.1.RELEASE), Spring Web MVC (4.2.6 RELEASE), Spring Security (4.0.3.RELEASE), Hibernate (4.2.21.Final), Hibernate Validator (5.3.0.Alpha1)

### Back-end Testing Framework
Mockito (1.9.5), Junit (4.11), Spring Framework (4.2.6.RELEASE)

### UI Testing Plugin
phantomjs-maven-plugin (0.4), jasmine-maven-plugin (1.3.1.5)

### Build Tool
Maven (3.3.9)

### Cache Library
Guava Cache (19.0)

## Installation
Installation is easy, first you can run Maven clean install terminal command:
```
mvn clean install
```

Then you can jetty:run the sub-module project in order to startup the Web Application:
```
mvn jetty:run -pl pwa-webapp
```

Finally access the Web Application via URL:
```
http://localhost:8080/
```
