[![Todo.ly Pipeline](https://github.com/AT15-TestNG/todo.ly/actions/workflows/pipeline.yml/badge.svg)](https://github.com/AT15-TestNG/todo.ly/actions/workflows/pipeline.yml)

## Todo.ly API Testing

This repository was done for [Todo.ly](https://todo.ly/) API Testing. 
It is an automated framework implemented in Java, and it is used to test the Todo.ly API.
The following dependencies were used:
* [Rest Assured](https://rest-assured.io/)
* [TestNG](https://testng.org/)
* [SonarQube](https://www.sonarqube.org/)
* [Allure](https://www.allure.io/)

### Getting Started

Before to run this project you need the following tools installed on your machine:
* [Java](https://www.java.com/) version 16 or higher
* Some IDEs like [IntelliJ IDEA](https://www.jetbrains.com/idea/) or [VS Code](https://code.visualstudio.com/)

Clone the repository on your machine and run the following command to run all the tests:
```
$ ./gradlew clean test
```

### Code Inspection
To ensure the quality of the code, It was used a static analysis tool like [Sonarcloud](https://sonarcloud.io/code-quality).
This is part of the Continuous Integration process, and it is run every time new code is pushed in any branch of this
repository.
If you want to run sonarqube analysis locally, firstly you need to configure a SONAR_TOKEN as a environment variable
in your system. After that, run the following command:
```
$ ./gradlew sonarqube
```

### Continuous Integration pipeline
CI pipeline is written in GitHub Actions, and it is used to run the tests, analyze the code quality in Sonarcloud, 
generate the reports and publish the reports on GitHub pages. You can see the testing reports in 
[Allure Reports](https://at15-testng.github.io/todo.ly/).

### More
This framework is part of AT15 API Testing subject, and was implemented by:
* [Adrian Oviedo](adrian.oviedo@fundacion-jala.org)
* [Agustin Mediotti](agustin.mediotti@fundacion-jala.org)
* [Jimy Tastaca](jimy.tastaca@fundacion-jala.org)
* [Saul Fuentes](saul.fuentes@fundacion-jala.org)
* [Sergio Mendieta](sergio.mendieta@fundacion-jala.org)