# LogisticHelper
An application that supports running a logistics company.
User capabilities in this application:

1) Truck management
- Addition of truck data
- Viewing truck data
- Editing truck data
- Deletion of truck data
- Monitoring the condition of the fluid and the truck tires
- Controlling the condition of the fluid and the truck tires

2) Travel management:
- Adding trip data to the trip list
- Viewing trip data from the trip list
- Editing trip data in the trip list
- Delete trip data from the trip list
- Data filtering
- Viewing filtered trip data
- Extracting all trip data to an .xlsx file for further analysis
- Extracting filtered trip data to an .xlsx file for further analysis

## Build and Run (Dockerizing)
To run the application must be installed docker and docker-compose

Inside the root folder of the project, execute commands from the console:

- windows:

```
docker-compose build
docker-compose up
```

- linux:

```
docker-compose -f docker-compose-nix.yml build
docker-compose -f docker-compose-nix.yml up
```

To run some of services add the name of servise (main-db, spring-WebAPI, angular-WebUI)

```
docker-compose up 
```

### Warning! If one of the ports is already busy, not all items will be running.

## Usage

Application running on the following ports:

- frontend - 80 (http://127.0.0.1:80)
- backend - 8081 (http://127.0.0.1:8081)
- mongodb - 27017 (http://127.0.0.1:27017)
- Backend - swagger_v2: http://127.0.0.1:8080/swagger-ui/index.html
