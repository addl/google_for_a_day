# Google For A Day

A simple prototype to crawl the WWW. Make your own modifications and don't hesitate to share it with us.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

#### For the Server API

##### Set up database
You will need PostgreSQL 9.6

To install PostgreSQL run the following command:

```
sudo apt-get install postgresql-9.6 postgresql-client-9.6 
```

Configure an user for use PostgreSQL, create a new Unix user with name 'safety_user' and password: 'safety_pass'. It is the default configuration for the Api Server.

```
sudo createuser safety_user
```

Fill out the information. Then authenticate as user 'postgres':

```
su postgres
```

Create a database with name 'google_for_a_day' and stablish as owner 'safety_user'. As 'postgres' user run:

```
createdb -O safety_user google_for_a_day
```

You will need load an schema from the google_for_a_day.sql file located inside 'server_app' directory. So make sure your are inside the folder containing the sql file and run:

```
psql -d google_for_a_day -a -f google_for_a_day.sql
```

If no error, the database is already configured.

##### Set up Java
You you will need Java 1.8+ or OpenJDK 1.8

In order to install java on Ubuntu or Debian you can run:

```
sudo apt-get install openjdk-8-jdk openjdk-8-jre-headless
```

##### Set up Maven
Install maven from Unix repositories, run:

```
sudo apt-get install maven
```

More details [here](https://www.mkyong.com/maven/how-to-install-maven-in-ubuntu/)

Make sure maven is inst correctly, run 'mvn -version'


#### For the Client Angular 4 project

Node.js and npm are essential to Angular development.

[Get it](https://nodejs.org/download/release/) now if it's not already installed on your machine.
Verify that you are running at least node v7.x.x and npm 4.x.x by running node -v and npm -v in a terminal/console window. Older versions may produce errors.

We recommend nvm for managing multiple versions of node and npm.

Move into Client API directory 'client-ang4-app/' and install node dependencies:

```npm install```

That will install @angular-cli and will install a new command 'ng', that we'll use later.

### Installing

#### Compiling the Server API
Let's compile the source code to execute the Server API. Move into directory 'server_app/' and run:

```
mvn package 
```

Wait, when finish, move into directory 'server_app/target' and execute the file 'server_app.0.0.1-SNAPSHOT.jar', run:

```java -jar server_app.0.0.1-SNAPSHOT.jar```

If everything is fine, you will see a banner with SpringBoot. Finally open your browser on [http://127.0.0.1:8000](http://127.0.0.1:8000).

#### Compiling the Client Angular 4 App

Move into Client API directory 'client-ang4-app/' and execute:

```ng server```

Wait for a moment, if a successful message is shown, open your browser with the url [http://127.0.0.1:4200](http://127.0.0.1:4200)

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

* [SpringBoot](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Angular4](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **D. Lion, Andry** - *Initial work* - [MyGithub](https://github.com/addl)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc
