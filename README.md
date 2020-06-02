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

<details>
<summary>
Sample output
</summary>

```sh
>  $ sh scripts/shell/example.sh
Creating a new account
curl -sX POST http://localhost:8080/accounts -d @scripts/json/account.json -H "Content-type: application/json" | grep -Po '(?<=id":")[^"]+'
Account UUID created
b0954aa6-9381-412b-87a0-4c849a562dbb

Querying this ID b0954aa6-9381-412b-87a0-4c849a562dbb
curl -sX GET http://localhost:8080/accounts/b0954aa6-9381-412b-87a0-4c849a562dbb
{"document_number":"06388715907","id":"b0954aa6-9381-412b-87a0-4c849a562dbb"}

Creating account error example
curl -sX POST http://localhost:8080/accounts -d @scripts/json/account-error.json -H 'Content-type: application/json'
Account invalid or not found

Creating a new transaction according this account UUID b0954aa6-9381-412b-87a0-4c849a562dbb
curl -sX POST http://localhost:8080/transactions -d '{ "account_id": "b0954aa6-9381-412b-87a0-4c849a562dbb", "operation_type": "1", "amount": 123.45 }' -H 'Content-type: application/json'
{"amount":-123.45,"id":"3eb28df5-7b10-4834-b7ca-54b4a50f78f9","account_id":"b0954aa6-9381-412b-87a0-4c849a562dbb","operation_type":1,"event_date":"2020-06-02T13:37:56.769392201"}

```
</details>

## Prerequisites
[![JDK](https://img.shields.io/badge/JDK-14.0.1--zulu-orange)](https://www.azul.com/downloads/zulu-community/?architecture=x86-64-bit&package=jdk)
[![Gradle](https://img.shields.io/badge/Gradle-6.4.1-brightgreen)](https://gradle.org/install/)
[![Docker](https://img.shields.io/badge/Docker-%3E%3D19.03.6-blue)](https://www.docker.com/)
[![Docker-compose](https://img.shields.io/badge/Docker--compose-%3E%3D1.21.0-blue)](https://github.com/docker/compose/releases)

## Table of Contents
* [TL;DR](#transactions)
* [Prerequisites](#prerequisites)
* [About the Project](#about-the-project)
* [Getting Started](#getting-started)
* [Testing](#testing)
* [Installation](#installation)
* [Usage](#usage)
* [Contact](#contact)
* [Acknowledgements](#acknowledgements)

## About The Project

This is a Java Spring Boot RESTFul application built in a Docker container, just for practicing these technologies and TDD. :smile:

## Getting Started

To run this project locally you must have the technologies as the [prerequisites section](#prerequisites)

### Testing
#### Unit tests
```sh
make unit-test
```

#### Integration tests
```sh
make integration-test
```

#### All tests
```sh
make all-test
```

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
