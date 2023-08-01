# Pet Management
## Requirements

For building and running the application you need:

- [JDK 20](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html)
- [Intellij](https://www.jetbrains.com/idea/)

## Running the application locally

Before running the application, make sure check and change application properties regarding H2 database. You can change
application properties in `src.main.resources.application.properties`

```
spring.datasource.url=jdbc:h2:file:/data/demo;AUTO_SERVER=TRUE
spring.datasource.username=sa
spring.datasource.password=password
```

## Usage
This backend works together with [FronEnd](https://github.com/AlladinIT/pet-management-frontend).
To run backend part, you need to open Intellij and run the project (make sure to open from root).




