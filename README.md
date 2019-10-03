# 360-feedback

[![Build Status](https://travis-ci.org/thiagoferrax/360-feedback.svg?branch=master)](https://travis-ci.org/thiagoferrax/360-feedback)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.agile%3Aback-end&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.agile%3Aback-end)
<a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/License-MIT-blue.svg"></a>

## Requirements overview

A project team wants 360 feedback. Team members belong to the project, but one member can belong to more than one project. The project is executed in a department of one of the company's units. All members need to use this system to give feedback to their teams and view the result of the feedback they have received.

A member can give more than one feedback to his team and see the progress of those feedbacks, as well as access the progress of the feedback the team has given him. A non-manager member will only be able to see feedbacks about himself, but can see how their rating is relative to the team and the company against a specified rating criteria.

Feedback is created by a team member who defines the items that need to be evaluated, as well as a rating scale for these evaluations. A feedback assessment is associated with a team and it will not be possible to perform the same assessment for more than one team at a time. Each feedback item has a name, a description and will be rated according to the rating scale. Items may have sub-items and their rating affects their parents' rating.

Each project has a manager and this manager will be able to view all feedback results from his team members, including his own. This manager may also have a manager above him and the latter manager will have access to all feedback from the teams under his supervision.

All members will be able to view the consolidated results of all projects in their department and company unit and the consolidated results for the entire company. The member can even see how they are doing with all members of their department and unit.

The system must provide real-time information so that team members can see their feedback while the team is conducting the evaluations.

Finally, the system must control user access and authorization.

## Architecture overview

#### Class diagram

![class-diagram](https://github.com/thiagoferrax/360-feedback/blob/master/requirements/classDiagram.png)

#### Object diagram

![class-diagram](https://github.com/thiagoferrax/360-feedback/blob/master/requirements/objectDiagram.png)

#### Tech stack
* [React](https://reactjs.org/) for creating the front-end application
* [UICore Template](https://coreui.io/) for using a free bootstrap admin template
* [Spring Boot](http://spring.io/projects/spring-boot) for creating the RESTful Web Services
* [MockMVC](https://spring.io/guides/gs/testing-web/) for testing the Web Layer
* [Mockito](https://site.mockito.org/) for testing the Services Layer
* [Postgres](https://www.postgresql.org/) as database
* [Maven](https://maven.apache.org/) for managing the project's build
* [Docker](https://www.docker.com/) for building and managing the application distribution using containers 

## Install
#### Download the repository
```sh
$ git clone https://github.com/thiagoferrax/360-feedback.git
```
#### With docker and docker-compose installed
```sh
$ cd 360-feedback && docker-compose up
```

## License

MIT © [thiagoferrax](https://github.com/thiagoferrax).
