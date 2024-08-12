# Playwright Java Api Test Automation Project

This project is a base for Java-based Playwright API test automation.

# Tool stack

* **Java/Javascript** - Development Language
* **IntelliJ IDE** - Development IDE
* **Maven** - Package Management
* **Playwright** - Test Automation Tool & Framework

# Documentation
* Initiate a Playwright Api Setup <a href="https://playwright.dev/docs/api-testing">documentation</a>

# Installations

* To install the necessary libraries , the following command is run in the project directory.
  ```
  mvn clean install
  ```

# Run Test

```
mvn clean test
```

# Reporting
```
allure serve allure-results 
```


# Project Folder Structure

```
.
|-- src
|   |-- main
|   |   |-- java
|   |   |   |   |-- data
|   |   |   |   |   |-- user
|   |   |   |   |   |-- users
|   |   |-- test
|   |   |   |-- delete
|   |   |   |   |-- DeleteUserApiTest
|   |   |   |-- get
|   |   |   |   |-- GetApi
|   |   |   |-- post
|   |   |   |   |-- CreateUserPostCallTest
|   |   |   |-- put
|   |   |   |   |-- UpdateUserPutApiTest
|-- git.ignore
|-- pom.xml
|-- README.md
```

# Naming Convention

We use `camelCase` for identifier names (variables and functions).

Use `PascalCase` only when naming constructors or classes

All names start with a letter.

At the bottom of this page, you will find a wider discussion about naming rules

```
folder name = my_folder

js file name = my_file.js

feature file name = my_feature.rb

class name = MyClass

function name = myMethod

variable name = myVariable

element name = btnMyElement, lblMyElement, txtMyElement

Enum = ALL_CAPITAL = 'value'

Constant = MY_CONSTANT

tag name = @my_tag 

```

# Tag Standards

```
@wip = Scenario / feature that has not been developed yet
@smoke = Scenario / feature expected to work within the scope of Smoke
@excluded  = Scenario / feature that no longer exists on the application, but whose scenario has been improved
@bug_fix = Scenario / feature pending bug fix due to an existing bug on the application
@prod  = Scenarios to run in the prod
@regression  = Scenarios to run in the regression
@feature_tag = The tag name to be given to each feature file
@scenario_tag = The tag name to be given to each scenarios
```

# Commit ve PR structure

```
* Branch names must be opened with the ticket id of the job. Example: QA-74
* As much as possible, commits should be committed with clear explanations.
* Commit should be progressed in as small pieces as possible (Atomic). This will make the rollback easier.
* It will be merged into the master after the review of the obligatory reviewers from the PR QA team.
* Before merging to the master, the pipeline will be run on the relevant branch and it will be verified that there is no problem.

```