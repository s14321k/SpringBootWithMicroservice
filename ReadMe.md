## Basics To Know
- Updating pom.xml, give run as Maven clean, then Maven install and then Run as spring boot application.

## intellij Settings

---
### intellij Debug enable

#### ✅ What you should do to get changes reflected in debug mode:

1. **Enable "Build project automatically"**:
    - **File > Settings > Build, Execution, Deployment > Compiler**
    - ✅ Check **Build project automatically**.

2. **Enable "HotSwap" when debugging**:
    - **File > Settings > Build, Execution, Deployment > Debugger > HotSwap**
    - Set it to **"Always reload classes"** (instead of "Ask" or "Never").

3. **(IMPORTANT)**: When you change Java files:
    - Save the file (`Ctrl+S` / `Cmd+S`).
    - Press **Ctrl+F9** (`Build Project`) to compile changes.
    - IntelliJ should **HotSwap** the modified classes automatically into the running debug session.

4. **If HotSwap doesn't happen automatically**:
    - In Debug mode, click on this icon in the toolbar: 🔄 (Reload Changed Classes).

---

**Still not reflecting?**


```properties
spring.devtools.restart.enabled=false
```


---
### To Add all the project to intellij as spring boot
- Right click on pom.xml on each project and select add to maven module
- For gradle, something similar to that.

## 404, whitelable issue

https://stackoverflow.com/a/53603984/11962586

- Jsp Pages should be under resourcees->Meta-inf folder

## Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jdbc</artifactId>
</dependency>
```

## Database properties

#Database connection
spring.datasource.url=jdbc:mysql://localhost:3306/reservation
spring.datasource.username=sarath69kumar
spring.datasource.password=sarath69kumar

spring.jpa.show-sql=true

# https://stackoverflow.com/q/36605104/11962586

# Stops auto deletion of inserted values in database

spring.jpa.hibernate.ddl-auto=none
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.data.jpa.repositories.enabled=true

spring.jpa.generate-ddl=true

# DB Auto Run sql queries

spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath*:/WEB-INF/db/*.sql

# Sleuth is depricated so micrometer and zipkin
