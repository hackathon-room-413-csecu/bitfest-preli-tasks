# Challenge_2 Setup Documentation

This document provides the steps required to set up and run the `challenge_2` project.

---

## Prerequisites

1. **Java Development Kit (JDK):** Ensure that JDK 17 or above is installed.  
   [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)

2. **Maven:** Ensure Apache Maven is installed.  
   [Download Maven](https://maven.apache.org/download.cgi)

3. **PostgreSQL Database:** Set up a PostgreSQL instance or use the provided Supabase database connection.

4. **Git:** Ensure Git is installed to clone the repository.  
   [Download Git](https://git-scm.com/)

---

## Local Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd challenge_2
```
2. Configure Secrets
Ensure that the secrets are stored in a secrets.yaml file in the src/main/resources directory. The format should be:
```yaml
secrets:
  datasource:
    url: ...
    username: ...
    password: ...

```
A sample secrets.yaml is added for convenience.

3. Build the Project
Navigate to the project root directory and run:
```bash
mvn clean install
```

4. Run the Application
To start the Spring Boot application, use:
```bash
mvn spring-boot:run
```
