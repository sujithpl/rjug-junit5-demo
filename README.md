# 5 Reasons to Switch to JUnit 5
*Richmond Java Users Group, June 2018*

## Instructions

Follow along the commit history chronologically as I show you how to start using the features of JUnit 5.

## Abstract

Even though it bears many similarities to JUnit 4, the new version of JUnit is not just an incremental update. Version 5 of the JUnit framework has been completely rewritten with the aim of improving the testing experience of modern Java developers. In this talk, I will highlight a few exciting features of the framework and demonstrate their practical application so that you can start using JUnit 5 tomorrow.

## Presentation

### Why a new version of JUnit?
- Modern
  * Expectations around testing have advanced in last decade
  * Support for Java 8 (and beyond)
- Modular
  * No more single fat JAR
  * Separation of concerns – discovery versus execution
  * Maintainability
- Extensible
  * Easier to customize
  * Open up for innovation

### Architecture
- JUnit Platform
  * `junit-platform-launcher`
    - Foundational APIs used by IDEs and build tools to launch a testing framework
  * `junit-platform-engine`
    - APIs for integration of any testing framework
- JUnit Jupiter
  * `junit-jupiter-api`
    - APIs for the new programming model and extension model
  * `junit-jupiter-engine`
    - To discover and execute Jupiter tests
- JUnit Vintage
  * `junit-vintage-engine`
    - To discover and execute JUnit 3 and 4 tests

### JUnit 5 provides new ways to …
   
#### Report output of tests    
- Display Names - `@DisplayName`

#### Use Java 8 features    
- AssertAll
- AssertThrows
- Optional Message position

#### Structure tests    
- Nested Test Classes - `@Nested`
- `@BeforeAll, @BeforeEach, @AfterEach, @AfterAll`
- Tags - `@Tag`

#### Write cleaner tests 
- Repeated Tests - `@RepeatedTest, RepetitionInfo`
- Parameterized Tests - `@ParameterizedTest, @ValueSource, @EnumSource, @CsvFileSource, @MethodSource`

#### Execute tests 
- Tooling - IDE, console runner, Maven/Gradle/Ant
- JUnit 4 support