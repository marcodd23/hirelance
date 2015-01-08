hirelance
=========

Java EE  Spring MVC Social Network

##How To:

* Clone the project 
* $ **mvn clean install**
* $ **mvn cargo:run**
* go to: **http://localhost:7070/hirelance/**

#Back-End administration  credentials:
* user: admin
* password: admin

##Description

[Hirelance](http://hirelance-goodbytes.rhcloud.com/) is a demo of social network to connect the world of freelancing with that of employers. 
It is based on J2EE Technologies , with Spring MVC, Jsp and Apache Tiles for the Presentation Layer and Business Tier, JPA (EclipseLink) for the Persistence Layer. The DB is Oracle XE11g. A User can create an account to the platform with Username and Password. After that he can chose to create a "Freelance" profile , "Employer" profile or both. 
An Alias name is associated with each profile , that is the name with which other user can see it. 
A freelance can find a projects to work on, and apply. An employers can post a project and search for 
a freelance to work with. At the end of a project, freelance and employee leave a feedback each other.
They can comunicate together with an internal chat, with facebook style notification, 
but only the Employer can start the conversation

The Use-Case diagram:

![alt tag](https://bitbucket.org/repo/zR9Xbn/images/3576652122-use_case.png)


The Domain Model:

![](https://bitbucket.org/repo/zR9Xbn/images/1889302071-class_model.png)



