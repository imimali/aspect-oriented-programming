# AspectOrientedProgramming
Work I've done for my Aspect Oriented Programming class at uni. Here you can find the lab requirements this project is aimed to solve.
## Lab 1: Logging/Tracing
- Design and implement an application (with the Observer pattern). Use a database for persistent data.
- Trace the execution of each public method using a logging/tracing tool (eg. java.util.Logging (included in JSDK), Log4J).
## Lab 2: Tracing with AOP
- Modify the previous system from lab1, to trace the methods using AspectJ.
- Inside the aspect, modify the library used for tracing (Logging ->Log4J, Log4J->Logging).
## Lab 3: Observer with AOP
- Modify the problem with the Observer pattern to use the AOP-based implementation of the Observer design pattern. 
- Define an annotation, annotate the methods that change the state of the subject, and define a pointcut using the annotation.
## Lab 4: Spring and Spring AOP
- Modify the problem  to use the Spring Framework and databases (with Spring JDBC).
- Use Spring AOP for performance monitoring and caching the objects retrieved from the database.
## Lab 5: Spring Security
- Modify the solution from Lab4 to add authentication to service methods that modify your domain classes (addition, removal, etc.)
- Use the annotation-based solution to select the service methods in the pointcut. 
