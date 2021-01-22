Coverage: 72.3%
# Inventory Management System (IMS) Project

The objective of this project is to develop an application using Java to interact with a SQL database within a Google Cloud Platform (GCP) instance. The application will enable the user to interact with the application via the command line. The application enables the user to Create, Read, Update and Delete entries within the IMS database.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What you need to install the software and how to install them


To run this application, you will need:
* Java installed 
* Eclipse IDE
* Maven
* MySQLWorkbench for a local SQL database connection
* Google Cloud Platform (GCP) instance for a remote SQL connection


### Installing

How to get the development environment running:

1. Clone or fork this repository
2. Import folder as a Maven project within Eclipse

In order to run the Java application within the command line enter the following into the command line and press enter: 
```
$ java -jar calfonso-ims-0.0.1-jar-with-dependencies.jar
 
```

![](Supporting%20Documents/OrderReadScreenshot.png)

## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

JUnit is used for testing this application. In order to run these tests, right-click on the project within the Eclipse IDE and select "Coverage As", then select "JUnit Test"

## Deployment

1. Open a command line within the project folder i.e. change directory within the command line to "/your-directory/ims-demo/"
2. Enter the following:
```
$ mvn clean package
 
```
3. Then change directory into target using
```
$ cd target 
```
4. Run the application using:
```
$ java -jar calfonso-ims-0.0.1-jar-with-dependencies.jar
 
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/RafSobolQAC/ims-demo)
* **Jordan Harrison** - *Updated-base* - [jordanharrison](https://github.com/JHarry444/ims-demo)
* **Claes Alfonso** - *Implementation and development of working application*

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* **Jordan Harrison** - *Updated-base creator*
* **Nick Johnson** - *Git and Project Management Trainer*
* **Vinesh Ghela** - *Java Development and Testing Trainer*
* **Aswene Sivaraj** - *Java Development and Testing, SQL Trainer*

