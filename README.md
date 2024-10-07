# Task Requirements

Write a microservice application, that implements a ‘CRUD’ with REST endpoints for JSON entity (not for all entities,
only for root entity) with the structure:
The root object is the company.     
Each company has name and departments.  
Each department has name and contains teams.    
Each team has name and project.     
Each project has a manager with contact information.

**Using**: Java 21, Spring, Postgres, Maven, GIT (local repository), Docker (just to start app) and any other    
technologies (for some converting, for example)

**Note**: keep it simple and clear, fulfilling the minimum requirements.
Push the project to the GitHub repository and share the link with us

Bonus rear (not required): write tests for the service using any technologies and approaches that you consider relevant
for checking basic functionality.
-----------------------------------------------------------------------------------------------------------------------

# Getting Started

### Prerequisites

• Java 21   
• Docker    
• Maven

### Installation

Clone the repository:

```bash
git clone https://github.com/Alex777x/luxmed.git
```

Navigate to the application folder:

```bash
cd luxmed
```

Run Maven command:

```bash
mvn clean package
```

### Running the application

To run the application, execute the following command:

```bash
docker-compose up --build
```

The application will be available at `http://localhost:8080`.

To stop the application, execute the following command:

```bash
docker-compose down -v
```

### Tests

Tests are written using JUnit 5 and Mockito. To run the tests, execute the following command:

```bash
mvn test
```
