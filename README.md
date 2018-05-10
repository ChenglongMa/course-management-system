# High-Five: Course Management System

## Preface

The project has been greatly changed, including that:

1. The **build tool** was moved from **Gradle** to **Maven**.
2. The **spring** framework was introduced in the project.
3. The **MySQL** database was applied for the project.
4. The **project structure** has been changed to follow the principles we learned in SEF (e.g., MVC, Information expert)

## Project Structure

team
 +- high5
    +- CourseManagementSystemApplication.java  
      |
      +- domain
      |  +- Admin.java
      |  +- Student.java
      |  +- ...
      
      +- repository 
      |  +- AdminRepo.java
      |  +- ...
      
      +- service
      |  +- AdminService.java
      |  +- ...
 
      +- web     
      |  +- AdminController.java
      |  +- ...

### Sketch

**CourseManagemnetSystemApplication.java** is the **Startup class**

The domain package is the data layer responsible for transferring data among the whole project.

The repository package is the 

| Package/Class                          | **Responsibility** | Description                     |
| -------------------------------------- | :----------------- | :------------------------------ |
| CourseManagementSystemApplication.java | Startup class      | The launch class of the project |
| domain                                 | Data layer         | Transferring data among layers  |
| repository                             | Database logic     | Database access                 |
| service                                | Business layer     | Process business logic          |
| web                                    | Presentation layer | The views/UI                    |

The project started at model classes mixed the **data** and the **logic** before which broke the MVC principle so that I divided them into **domain** and **service** package which are responsible for **data** and **logic** respectively. In addition, as the requirement of the project, the project introduced **MySQL database** and **spring boot framework** to build a integrated **web-base** system.

## TO DO

There are still some problems in the project so that we need to keep refactoring it.

1. Complete and fix the logic of the system ( i.e., add/remove/move/update the methods in each class of **service** package.)
2. Finish the UI design.
3. Fix the **Class diagram**, **interaction diagrams**, etc.
4. Modify the **Unit Test** logic respectively.
5. Build and test our project.
6. Have fun!

------------------------------------------------------------------

Chenglong Ma

10 May. 2018
