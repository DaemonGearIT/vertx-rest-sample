**Vertx Sample Rest**

A sample rest api with Vertx.io


Run test
```
mvn clean test
```

Package and run
```
mvn clean package
java -jar target/trackit-api-1.0-SNAPSHOT-fat.jar
```

Package and run with configuration file
```
mvn clean package
 java -jar target/trackit-api-1.0-SNAPSHOT-fat.jar -conf src/main/conf/trackit-api-conf.json
```