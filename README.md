# Trafiklab
Hey and welcome to my small spring boot app for token secured REST endpoints.
There is the possibility to create and login user as well as get last logins

First thing first how do I get this project going?

## Requirements
You need the following installed on your machine and available through the terminal
* [Java 17](https://openjdk.java.net/projects/jdk/17/)
* [Maven 3.8.3](https://maven.apache.org/download.cgi)

## Usage
There are several ways you can build and run this application.
The easiest way is to import this into IntelliJ and run
```
UserAuthenticationApplication.java (Press Ctrl + Shift + F10 inside the java class)
```
You can also build and run the application through the terminal  
In root (root being UserAuthentication) category do the following
```shell
# builds the application
mvn install
```
```shell
# starts the application
mvn spring-boot:run
```

Then the application is up and running on localhost:8080/

### Available endpoints
* /public/users/register
* /public/users/login
* /users/current

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)