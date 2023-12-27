# Lombok

## Introduced getters and setters
### Reducing codes

https://stackoverflow.com/a/11807022/11962586


# Login page for sample
https://www.javaguides.net/2018/10/user-registration-module-using-springboot-springmvc-springsecurity-hibernate5-thymeleaf-mysql.html

# Logger in properties and in code.
* Types of logs to be shown in console.
* Save the logs in a folder named Logs in the same application

# Constant values
* In application.proerties setting the constant values and getting it by using @values.

# BCryptPasswordEncoder
* Before saving inside the DB we are encoding the password.

# WebSecurityConfigurerAdaptor 
* [is depricated in spring 3.0](https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter/)

# Jar cmds
* Right click the project 
 -> Run as -> Maven clean
 -> Run as -> Maven Install
 Now check target folder, now you can see flightReservation.jar. Refresh if you cant see
 
We can use this jar file to run in any server using the cmd -> java -jar flightreservation.jar

# War
- Change jar to war in pom.xml
- in @SpringBootApplication class extend it with SpringBootServletInitializer.

# StreamingResponseBody for file streaming
* [LINK](https://stackoverflow.com/q/51845228)