# Sample for Spring Data JPA issue 3756

See https://github.com/spring-projects/spring-data-jpa/issues/3765

## How to reproduce

Run the following command and the test should fail with the error below
```shell
mvn clean test
```

> [ERROR] Errors:
> [ERROR]   TaskListRepositoryTest.test:29 » InvalidDataAccessApiUsage org.hibernate.query.SemanticException: Query specified join fetching, but the owner of the fetched association was not present in the select list [SqmListJoin(com.example.demo.entities.TaskList(5).taskItems(6))]
