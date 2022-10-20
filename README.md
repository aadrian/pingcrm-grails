# Ping CRM on Grails
A demo application built with **[Grails](https://grails.org)** and **[Vue.js](https://vuejs.org)** to illustrate how **[Inertia.js](https://inertiajs.com/)** works.\
This is a port to Grails/Groovy of the [original Ping CRM](https://github.com/inertiajs/pingcrm) written in Laravel/PHP.\
It uses the [Grails Adapter for Inertia.js](https://github.com/matrei/grails-inertia-plugin) plugin.

![Screenshot of the Ping CRM application](screenshot.png)

> There is a hosted installation of this demo application available at:\
> https://pingcrm.mattiasreichel.com
> 
> The demo is restarted and the database is wiped and reseeded every hour.\
>**Please be respectful when editing data**.

## Requirements
- Java 8+
- Npm

## Installation
Clone the repo locally
```shell
me@my:~$ git clone https://github.com/matrei/pingcrm-grails.git
me@my:~$ cd pingcrm-grails
```
Install client dependencies
```shell
me@my:~/pingcrm-grails$ npm install
```
## Running
### In development mode ...
Serve client files with hot module reloading
```shell
me@my:~/pingcrm-grails$ npm run serve
```
and start the grails application
```shell
me@my:~/pingcrm-grails$ ./gradlew bootRun
```
###  ... or in production mode
with minified client files
```shell
me@my:~/pingcrm-grails$ ./gradlew -Dgrails.env=production bootRun
```

### You're ready to go!
Visit Ping CRM in your browser - http://localhost:8080

## Running tests
To run the Ping CRM test suite, run:
```shell
me@my:~/pingcrm-grails$ ./gradlew check
```

## Build for production
Compiles and minifies and creates a runnable jar for production (in build/libs)
```shell
me@my:~/pingcrm-grails$ ./gradlew assemble
```

## Credits
* Port to Grails by Mattias Reichel (@mattias_reichel)
* Original work by Jonathan Reinink (@reinink) and contributors
