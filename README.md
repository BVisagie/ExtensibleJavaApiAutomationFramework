# TA Playground - API Automation for Java

The purpose of this Framework is to serve as a functional demo and / or template to be used on other projects KZA might
undertake and assist with training.

This framework showcases two API integrations for testing:

* [{JSON} Placeholder](https://jsonplaceholder.typicode.com/) Free fake API for testing and prototyping.
* [Restful-booker](https://restful-booker.herokuapp.com) an API that you can use to learn more about API Testing or try
  out API testing tools against.

## Description

The API automation framework for Java project consists of the following primary elements and technologies:

* [RestAssured](http://rest-assured.io/) library to test REST APIs
* [JUnit 5](https://junit.org/junit5/) to support the test creation
* [AssertJ](https://assertj.github.io/doc/) fluent assertions for all test cases
* [Owner](https://github.com/matteobaccan/owner) to manage the property files
* [Allure Report](https://docs.qameta.io/allure/) as the testing report strategy

[![allure-report-example.jpg](https://i.postimg.cc/C50rR6Qt/allure-report-example.jpg)](https://postimg.cc/xJs37gYy)

## Installation and Usage

### Required software

* Java JDK 20+
* Maven installed and in your classpath ([Installing Apache Maven](https://maven.apache.org/install.html))
* Allure command line installed using
  Scoop ([Installing Allure](https://docs.qameta.io/allure/#_installing_a_commandline))

### Installing

* Running a `mvn clean package` should be enough to get the project built and ready to use.
* If you wish to skip the running of all tests run: `mvn package -DskipTests`.

### Executing tests

* Right-click a test class file (example:`nl/kza/restful_booker/tests/AuthenticationTests.java`) and select `Run`
  or `Debug`
* Or Run `mvn clean test`

### Generating an Allure report

* Using terminal within your IDE run: `allure serve target/allure-results` _(please take note
  that **Allure command line** needs to have been installed for report generation to succeed)_
* If no report is visible after running the command check if the report data is being generated after test execution
  within `target/allure-results`

### Module Overview

* API Automation Framework
    * This is the primary component of the automation framework, all actual tests live in this module.

### Authors

* Bernard Visagie

### License

MIT License

Copyright (c) 2023 - Bernard Visagie

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.