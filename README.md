Hirelance
=========

Java EE  Spring MVC Social Network

##Description

[Hirelance](http://hirelance-goodbytes.rhcloud.com/) is a demo of social network to connect the world of freelancing with that of employers, developed as University Project. 
It is based on J2EE Technologies , with Spring MVC, Spring Security, Jsp and Apache Tiles for the Presentation Layer and Business Tier, JPA (EclipseLink) for the Persistence Layer. A User can create an account to the platform with Username and Password. After that he can chose to create a "Freelance" profile , "Employer" profile or both. 
An Alias name is associated with each profile , that is the name with which other user can see it. 
A freelance can find a projects to work on, and apply. An employers can post a project and search for 
a freelance to work with. At the end of a project, freelance and employee leave a feedback each other.
They can comunicate together through Messaging System, with facebook style notifications, 
and the rule is that only the Employer can start the conversation

##How To:
If you want to run the web-app on localhost, follow these steps:
* Create Database on MySql:
  * CREATE DATABASE **hirelancedb**;
  * CREATE USER '**hirelanceuser**'@'localhost' IDENTIFIED BY '**hirelancepassword**';
  * GRANT ALL PRIVILEGES ON hirelancedb.* TO 'hirelanceuser'@'localhost' WITH GRANT OPTION; 
* Clone the project 
* $ **mvn clean install**
* $ **mvn cargo:run**
* The first time go to: **http://localhost:7070/hirelance/init/populate** to generate DB tables and populate them with some data.
* Go to the home: **http://localhost:7070/hirelance**
* Back-End administration  credentials:
  * **user**: admin
  * **password**: admin

####The Use-Case diagram:

![alt tag](https://bitbucket.org/repo/zR9Xbn/images/3576652122-use_case.png)



####The Domain Model:

![](https://bitbucket.org/repo/zR9Xbn/images/1889302071-class_model.png)



