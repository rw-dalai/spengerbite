# SpengerBite

A food delivery app. Spring Boot 4 backend, React frontend in a separate repository.

## Requirements

- JDK 25, check with `java -version`

## Run

```bash
./gradlew :app:bootRun                      # start SpengerBite on http://localhost:8080
./gradlew :app:test                         # run the tests
./gradlew build                             # compile, test and package both modules
```

H2 console on http://localhost:8080/h2-console, JDBC URL from the boot log.

## Modules

| Module | What it is |
|---|---|
| `app` | SpengerBite |
| `jpa-demo` | JPA exercises, a person with passport and trips, independent of app |

Tasks take the module as prefix, `:app:test`, `:jpa-demo:bootRun`.
Without a prefix a task runs in every module that has it.

## Project layout

Four packages, one per layer. Today only `model` exists.  
Dependencies point downwards: api uses service, service uses repository and model, model uses nothing.

```text
app/src/main/java/at/spengergasse/spengerbite/
  api/                    API Endpoints
  service/                Business Use Cases
  repository/             Data Access Layer
  model/                  Domain Model
docs/                     Documentation
```

## Conventions

- No setters. State changes only through the constructor and business methods.
- Records for value objects and rich types, classes for entities, Lombok `@Getter` only.
- `Guard` checks arguments and normalizes them. Business rules go to `Rules`.
- Test names `Sut_ShouldExpectation_WhenCondition`, body Given, When, Then.
- Commits `type: message`, lower case, present tense. Types: feat, fix, build, test, docs, refactor.

## Gradle cheat sheet

```bash
./gradlew tasks                             # list all tasks
./gradlew build -m                          # show what would run, run nothing
./gradlew :app:test --tests "*GuardTest"    # one test class
./gradlew :app:test --rerun                 # force the tests to run again
./gradlew build --refresh-dependencies      # re-check every dependency
./gradlew -q javaToolchains                 # which JDKs Gradle found
```
