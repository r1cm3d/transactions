[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![GitHub issues](https://img.shields.io/github/issues/ricardomedeirosdacostajunior/transactions)](https://github.com/ricardomedeirosdacostajunior/transactions/issues)
[![Kanban Board](https://img.shields.io/badge/Kanban-%20Board-red)](https://github.com/ricardomedeirosdacostajunior/transactions/projects/1)
![Code Coverage](https://img.shields.io/badge/coverage-100%25-green)\
![Build](https://img.shields.io/badge/build-passing-green)
[![LinkedIn](https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555)](https://www.linkedin.com/in/ricardo-medeiros-da-costa-junior-18773246/)
# transactions
**TL;DR:**
```console
make && sh scripts/shell/example.sh
```
## Prerequisites
[![JDK](https://img.shields.io/badge/JDK-14.0.1--zulu-orange)](https://www.azul.com/downloads/zulu-community/?architecture=x86-64-bit&package=jdk)
[![Gradle](https://img.shields.io/badge/Gradle-6.4.1-brightgreen)](https://gradle.org/install/)
[![Docker](https://img.shields.io/badge/Docker-%3E%3D19.03.6-blue)](https://www.docker.com/)
[![Docker-compose](https://img.shields.io/badge/Docker--compose-%3E%3D1.21.0-blue)](https://github.com/docker/compose/releases)

## Table of Contents

* [About the Project](#about-the-project)
* [Getting Started](#getting-started)
* [Prerequisites](#prerequisites)
* [Installation](#installation)
* [Usage](#usage)
* [Contact](#contact)
* [Acknowledgements](#acknowledgements)

## About The Project

This is a Java Spring Boot RESTFul application built in a Docker container, just for practicing these technologies and TDD. :smile:

## Getting Started

To run this project locally you must have the technologies as the [prerequisites section](#prerequisites)

### Installation
#### In a development environment

```sh
make all-local
```
It will build Postgres and the application docker image and run them.

#### In "production" environment
```sh
make all
```
or just
```sh
make
```
It will build the stack compose, that is, build and run docker container with the Postgres database and the application.

## Usage

* [accounts](documentation/post-accounts.md) : `POST /accounts`
* [accounts](documentation/get-accounts.md) : `GET /accounts/:id`
* [transactions](documentation/post-transactions.md) : `POST /transactions`

## Contact

Ricardo Medeiros - [@rmedeiroscosta](https://twitter.com/rmedeiroscosta) - ricardo.medeiros.costa@gmail.com

Project Link: [https://github.com/ricardomedeirosdacostajunior/transactions](https://github.com/ricardomedeirosdacostajunior/transactions)

## Acknowledges
- https://github.com/jamescooke/restapidocs/tree/master/examples for REST documentation examples;
