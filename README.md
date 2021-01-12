# Ten Man Account Microservice

The account microservice maintains ten man webapp user accounts. It should provide a webapp to get and update details
about a particular user

## Dependencies

* Spring Boot
* Spring Boot Web
* Spring Data MongoDB
* Lombok

## Additional Technologies Used

* Docker

## Data Model

### Account

| Property          | Data Type  | Description                                        | Hashed? |
| ----------------- | ---------- | -------------------------------------------------- | ------- |
| username*         | String     | Accounts selected display name                     | false   |
| password*         | String     | Accounts selected password                         | true    |
| match history*    | List<Long> | List of match id's the account has participated in | false   |
| id                | Long       | Unique account identifier                          | false   |
| email*            | String     | Email address of account owner                     | true    |
| discord username* | String     | Account owners associated discord account          | true    |
| steam account*    | String     | Accounts associated steam account                  | true    |
| riot id*          | String     | Accounts associated riot id                        | true    |


\* - There is uncertainty currently around how to handle these fields. Subject to change

## Request Mapping
> /accounts

## Endpoints
---

> ### **GET** /accounts/{id}
Returns account details for account with specified id

Example return:
```
/accounts/0

{
    "_id": 0,
    "username": "account123",
    "password": "password123",
    "email": "AccountEmail@gmail.com",
    "discord_username": "discordUsername#1234",
    "steam_account": "https://steamcommunity.com/id/account123/",
    "riot_id": "riotAccount#1234",
    "match_history": [
        1251,
        1534,
        7357,
        8255,
        4725,
    ]
}
```

> ### POST /accounts/create
Creates a new account in the database with the json data in the request body

Example:
```
/accounts/create

Request Body:
{
    "username": "newAccountUsername",
    "password": "newPass1234",
    "email": "imNewHere@gmail.com"
}

A new account is created with the request body and entered into the database
```

>### DELETE /accounts/delete/{id}
Deletes the account with the specified id from the database

Example:
```
/accounts/delete/0

Account with id 0 is removed from the database
```

//TODO add more endpoints

## Build

### Environment Variables

| Variable Name        | Description                                        | Default Value |
| -------------------- | -------------------------------------------------- | ------------- |
| ACCOUNT_SERVICE_PORT | Specifies the port the account service will run on | 6091          |

### Setup

This service depends on an instance of MongoDb to operate, which can either be locally hosted or hosted on the cloud. The local and docker-local spring boot configuration expect an instance of MongoDb to be running on the same machine that the service or container is running on.

### Run with Docker

Run the following command from the root project directory

`docker build -t account-service:${VERSION} .`

To check that docker has built the image, you can run

`docker images`

If the image has been successfully created, you should see a newly created image with the `account-service` tag.

To run the docker image, run the command

`docker run -d -p ${ACCOUNT_SERVICE_PORT}:${ACCOUNT_SERVICE_PORT} ${IMAGE_ID}`

Replace ACCOUNT_SERVICE_PORT with the default value if you didn't specify a port, and replace the image id with the id of your container

***DONE!*** The account service should be running from your docker container.