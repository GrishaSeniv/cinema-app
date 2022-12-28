# 🎞️cinema-app 🎦

## Project description

A REST web-application that supports authentication, registration and other CRUD operations using MySQL as a database. 
It's based on the following technologies as: Hibernate framework, Spring Security and Spring MVC modules. 
Project was built according to SOLID principles: 
1) The  Dependency Inversion principle is achieved using  interfaces;
2) The Single Responsibility principle is achieved using mappers.

It uses JSON format for request and response. Also, it supports basic authentication. 
## 🎯 Features


    GUEST:
+ POST - /register - registration with USER(default) role
+ POST - /login - authentication like a user(with USER, ADMIN, or USER/ADMIN roles)


    ADMIN:
+ GET - /users/by-email, /cinema-halls, /movies, /movie-sessions/available
+ POST - /cinema-halls, /movies, /movie-sessions
+ PUT - /movie-sessions/**
+ DELETE - /movie-sessions/**


    USER:
+ GET - /orders, /shopping-carts/by-user, /cinema-halls, /movies, /movie-sessions/available
+ POST - /orders/complete
+ PUT - /shopping-carts/movie-sessions
## Project structure

Project was built according to 3-tier architecture - the presentation tier; the application tier, where data is processed; and the data tier, where the data associated with the application is stored and managed: 
- Controllers - Presentation tier
- Services - Application tier
- DAO - Data tier

The model level is represented in the following diagram:

![alt text](blob/cinema_diagram.png)


## 💻 Technologies:

- JAVA 11 or higher
- Maven 3
- Tomcat v.9.x.x
- MySQL v.8.x.x
- Spring WEB, SPRING Security v.5.2.2
- Hibernate v.5.4.27

## Installation

- Clone this project from GitHub;
- Install [MySQL](https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/);
- Install [Apache Tomcat v.9.x.x](https://tomcat.apache.org/download-90.cgi) ;
- Change URL, username, password and JDBC driver in [db.properties](src/main/resources/db.properties);
- Configure Tomcat server:
    1. Edit configuration;
    2. Tomcat Server -> Local
    3. Deployment -> add -> artifact -> cinema-app:war exploded
    4. Application context : /
    5. Press apply -> okay.
- A default user with ADMIN role is created in [DataInitializer](src/main/java/cinema/config/DataInitializer.java) when the project starts
