# Google For A Day

A simple prototype to crawl the WWW. Make your own modifications and don't hesitate to share it with us.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
1. For the Server API

Set up database
You will need PostgreSQL 9.6

To install PostgreSQL run the following command:

```
sudo apt-get install postgresql-9.6 postgresql-client-9.6 
```

Configure an user for use PostgreSQL, create a new Unix user with name 'safety_user' and password: 'safety_pass'. It is the default configuration for the Api Server.

```
sudo createuser safety_user
```
Fill out the information. Then create the database schema, authenticate as user 'postgres':

```
su postgres
```

Create a database with name 'google_for_a_day' and stablish as owner 'safety_user'. As 'postgres' user run:

```
createdb -O safety_user google_for_a_day
```


Set up Java
You you will need Java 1.8+ or OpenJDK 1.8

In order to install java on Ubuntu or Debian you can run:

```
sudo apt-get install openjdk-8-jdk openjdk-8-jre-headless
```


2. For the Client Angular 4 project

Node.js and npm are essential to Angular development.

[Get it](https://nodejs.org/download/release/) now if it's not already installed on your machine.
Verify that you are running at least node v4.x.x and npm 3.x.x by running node -v and npm -v in a terminal/console window. Older versions produce errors.

We recommend nvm for managing multiple versions of node and npm.

What things you need to install the software and how to install them

```
Give examples
```

### Installing

A step by step series of examples that tell you have to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc
