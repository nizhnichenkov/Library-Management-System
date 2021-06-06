NOTE: In order for the application to work, a database called 'library' must be created within the mysql container that is going to be picked up by docker-compose.

How to start the application: 
1. Remove /target directory: "rm -rf /target"
2. Clean, compile, package the app: "mvn clean; mvn compile; mvn package"
3. Docker compose: "docker-compose build"
4. Start the app: "docker-compose up"
5. Fire up any browser and type localhost:8080


