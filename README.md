
Familiarizing me with some JOOQ basics, this project is using an in-memory h2 database so nothing fancy.



## Project notes

To generate database code run.

```
mvn clean compile
```

## Notes about generated code

- Table classes
  - public class Author extends TableImpl<AuthorRecord>
  - Under tables package
- Record classes
  - public class AuthorRecord extends UpdatableRecordImpl<AuthorRecord>
  - Under tables.records package
- POJO classes
  - public class Author implements Serializable
  - Under tables.pojos package
  - NOTE: does not include one-to-many kind of relationships
  - CONCLUSION: Probably best to manage POJOs manually!!!
- DAO classes
  - Relies on JOOQ POJOs, reckon best to avoid and implement manually.

## Links

- Getting started
  - https://vrnsky.medium.com/spring-boot-jooq-getting-started-d313a12d776d
  - https://www.jooq.org/doc/latest/manual/getting-started/tutorials/jooq-with-flyway/
- JOOQ things
  - Code generation docs
    - https://jooq.org/doc/latest/manual/code-generation/
    - https://www.jooq.org/doc/latest/manual/code-generation/codegen-pojos/
- multiset and jsonagg usage 
  - https://vladmihalcea.com/fetch-multiple-to-many-jooq-multiset/