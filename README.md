# Lunch Recipe
User Story

As a User I would like to make a request to an API that will determine, from a set of recipes, what I can
have for lunch today based on the contents of my fridge, so that I can quickly decide what I’ll be having
to eat and the ingredients required to prepare the meal.

Acceptance Criteria

* GIVEN that I am an API client AND have made a GET request to the /lunch endpoint THEN I
should receive a JSON response of the recipes that I can prepare based on the availability of the
ingredients in my fridge
* GIVEN that I am an API client AND I have made a GET request to the /lunch endpoint AND an
ingredient is past its use-by date THEN I should not receive any recipes containing this
ingredient
* GIVEN that I am an API client AND I have made a GET request to the /lunch endpoint AND an
ingredient is past its best-before date AND is still within its use-by date THEN any recipe
containing this ingredient should be sorted to the bottom of the JSON response object
Additional Criteria
* Use the Spring Boot framework (https://spring.io/guides/gs/spring-boot/) to create the
application API
* The application SHOULD contain some basic unit and integration tests (e.g. using JUnit)
* The application MUST be completed using an OOP approach and engineering best practices
* The application code style MUST adhere to the Google Java Style Guide
(https://google.github.io/styleguide/javaguide.html)
* Any dependencies SHOULD be installed and configured using Maven (Maven is the preference,
but you may optionally use Gradle)
* Any installation, build steps, testing and usage instructions MUST be provided in a README.md
file in the root of the application - include instructions for building any Docker / Vagrant
environments if required

Application Data
For the purpose of this task, the application SHOULD fetch JSON data from the following mock
endpoints:
* Recipes: https://www.mocky.io/v2/5c85f7a1340000e50f89bd6c
* Ingredients: https://www.mocky.io/v2/5c9c33173600009f4ad96f2f

- - -

## Importing the Project
* Install java 8, Maven
* Download source code and unzip to your directory
* Open IntelliJ, Import the project -> Select the root folder -> Next
* Select ***Import project from external model*** -> Select ***Maven*** line -> Next
* Select ***tick automatically download source*** checkbox -> Finish 
    > If the code is not working, open the ***Maven tab***
    > Press ***Reimport all Maven projects***
 * you may need to install Lombok plugin on your IntelliJ IDE.

## Start Project using Command Line
    ```
    cd /directory/projectFolder
    mvn spring-boot:run
    ```

## Run the tests
* Open the `src/test` folder
* Right-click on `java` folder -> ***Run all tests**
* you can run tests using command line `mvn test`


## Using the API
`/lunch/{ingredients}`
you can test API on Using Postman
