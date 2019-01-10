# High-Five: Course Management System

## Preface

The project has been greatly changed, including that:

1. The **build tool** was moved from **Gradle** to **Maven**.
2. The **Spring Boot** framework was introduced in the project.
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

The roles of certain package are listed below:

| Package/Class                          | **Responsibility** | Description                     |
| -------------------------------------- | :----------------- | :------------------------------ |
| CourseManagementSystemApplication.java | Startup class      | The launch class of the project |
| domain                                 | Data layer         | Transferring data among layers  |
| repository                             | Database logic     | Database access                 |
| service                                | Business layer     | Process business logic          |
| web                                    | Presentation layer | The views/UI                    |

The project started at model classes mixed the **data** and the **logic** before which broke the MVC principle so that I divided them into `domain` and `service` package which are responsible for **data** and **logic** respectively. In addition, as the requirement of the project, the project introduced **MySQL database** and **Spring Boot framework** to build an integrated **web-based** system.

## *TO DO [done]*

There are still some problems in the project so that we need to keep refactoring it.

1. ~~Create a **branch** for yourself (e.g., "Chenglong" for Chenglong Ma)~~
2. Complete and fix the logic of the system ( i.e., add/remove/move/update the methods in each class of `service` package.). For this part, what you need to do are:
   1. Find your `XYZService.java` class and `XYZServiceImpl.java` class. (e.g., `StudentService.java` and `StudentServiceImpl.java`) which are **interface** and **implementation** class pair.
   2. Finish the logic in these class.
   3. **Note1**: You can merely call methods in the `XYZRepo.java` (e.g., `StudentRepo.java`) to access database.
   4. **Note2**: For other logic methods, you can call the methods in the `XYZService.java`(e.g., `CourseService.java`)
   5. When you complete your methods and push them to your branch, you need to Create a **New pull request** to notify others to review those codes if there were any conflicts with ours.
3. Finish the UI design.
4. ~~Fix the **Class diagram**, **interaction diagrams**, etc.~~
5. ~~Modify the **Unit Test** logic respectively.~~
6. ~~Build and test our project.~~
7. Merge all branches to **master** if there is no bug through testing.
8. Have fun!

------------------------------------------------------------------

Chenglong Ma

10 May. 2018
