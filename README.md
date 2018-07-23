# Tree-evaluation project for NetEnt
Sample code of tree evaluation

# Synopsis
A spring boot service that allows for the retrieval of the catalog information for a given model:
The logic tree that can be used to build a configurable model

# Technology Stack
Caching - Redis
Web Server - Jetty

# Framework(s)
Spring Boot
Spring MVC
Spring Test

# User Documentation
Swagger 


# Setup
Source Code Repository - Git

# Build System 
Maven

# Running Builds
-Download and Install Maven.
-Redirect your command shell to the directory in which the service resides in.
-Execute a clean build to be run.
           mvn clean package
This will run with all the unit tests being triggered

# Docker Containers
To build the container localler ensure you have installed Docker Toolbox for windows/mac/linux, than execute the following command:
mvn clean package && docker build -t evaluateTree:v1
To spin up your docker container you can execute the following:

# Dependencies
This is an independent module that does not require any other service to be up and running

# Redis
Used for centralized caching. It will not be used if you have the spring configuration spring.cache.type set to none, otherwise it will expect it to be accessible.

# Modules
The microservice is divided up into 2 parts:
-Service
A spring boot application that exposes end points for the building of a logic tree; and retrieval of an model's option(s) metadata.
-Test
Contains a spring boot application that contains unit tests that start up the service during each test and makes requests. Its only purpose is to prove out that the application can be stood up, all other testing should be in the system-test module.
