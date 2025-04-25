# Getting Started
## [YouTube](https://youtu.be/Wg6qWcDpoZU?si=rXXfnMoZOIbPD6sj)
 - [GitHub](https://github.com/atquil/spring-security/tree/JWT-oauth2)
 
## Annotations
	@EnableConfigurationProperties
	@ConfigurationProperties
	@PreAuthorize("hasAnyRole('ROLE_MANGER', 'ROLE_ADMIN', 'ROLE_USER')")
	-//- hasAnyRole is for Roles
	-//- hasAuthority is for permissions
	

## UserDetailsService
When user sign in, the username and password are retrieved from SecurityConfig.java in method `.userDetailsService`

## Authentication 

 `Authentication` object in Spring Security is **super central** to how security works — it basically represents the **currently authenticated user**.


---

### 🔍 What is `Authentication`?

In Spring Security, `Authentication` is an **interface** that holds information about the *current user* interacting with the system. It’s automatically populated by the Spring Security context when a user logs in or is authenticated via a token (like JWT).

---

### ✅ What it gives you:

When you inject `Authentication authentication` into a controller method, you get access to:

| Method                      | What it Returns                                           |
|----------------------------|-----------------------------------------------------------|
| `authentication.getName()` | The username (usually a `String`, like email or user ID) |
| `authentication.getAuthorities()` | A list of roles/permissions (like `ROLE_USER`, `SCOPE_READ`, etc.) |
| `authentication.getPrincipal()`  | The actual user object (can be a `UserDetails` or JWT token info) |
| `authentication.isAuthenticated()` | Boolean, whether the user is authenticated |

---

### 💡 Your example:

```java
@GetMapping("/welcome-message")
public ResponseEntity<String> getFirstWelcomeMessage(Authentication authentication) {
    return ResponseEntity.ok(
        "Welcome to the JWT Tutorial: " + authentication.getName() + 
        " with scope: " + authentication.getAuthorities()
    );
}
```


---

### 🧠 Why is this useful?

- You can customize responses or logic based on roles/permissions.
- You can extract the username or other details without having to manually decode tokens.
- It works with both session-based and token-based (JWT/OAuth2) authentication.


## Principal

Difference between `Principal` and `Authentication`.

Let’s break it down clearly 👇

---

### 🔍 What is `Principal`?

`Principal` is a **Java standard interface** (`java.security.Principal`) that simply represents the **identity of the current user**. It has just **one method**:

```java
String getName();
```

So in your example:

```java
public ResponseEntity<String> getManagerData(Principal principal) {
    return ResponseEntity.ok("Manager::" + principal.getName());
}
```

You’re getting the username of the currently authenticated user. This works perfectly fine for simple identity access.

---

## 🔐 What is `Authentication`?

`Authentication` is a **Spring Security interface** (extends `Principal`) and is much **richer** in terms of data.

It gives you:

| Feature                  | `Principal`         | `Authentication`               |
|--------------------------|---------------------|--------------------------------|
| Username                 | ✅ `getName()`       | ✅ `getName()`                  |
| Roles/Authorities        | ❌                   | ✅ `getAuthorities()`           |
| User object              | ❌                   | ✅ `getPrincipal()`             |
| Authenticated flag       | ❌                   | ✅ `isAuthenticated()`          |
| Credentials (e.g., token)| ❌                   | ✅ `getCredentials()`           |

So, `Authentication` **includes** what `Principal` gives, **plus more** — like roles, JWT claims, whether the user is authenticated, and other details.

---

## 🆚 Summary:

| Use When You Need...                  | Use This          |
|--------------------------------------|-------------------|
| Just the username                    | `Principal`       |
| Roles, token info, or full user data | `Authentication`  |

---

### ✅ Bonus Tip:
If you're working with JWT or OAuth2, and want custom claims like email, tenant, etc., you'll probably use:

```java
OAuth2AuthenticationToken auth
```

Or

```java
JwtAuthenticationToken jwtAuth
```




Understanding **Spring Security 6+** when dealing with different types of authentication (form login, basic, JWT, OAuth2, etc.). 

---

## 🚀 Overview of the Interfaces

| Interface                   | Description |
|----------------------------|-------------|
| `Principal`                | Java standard. Just gives you the username (via `getName()`). Very basic. |
| `Authentication`          | Spring Security’s main interface. Gives full access to auth data like roles, principal, credentials, etc. |
| `OAuth2AuthenticationToken` | Used when the user authenticates via **OAuth2 login** (e.g., Google, GitHub). |
| `JwtAuthenticationToken`  | Used when the user is authenticated via a **JWT token** (e.g., API with bearer token). |

---

## 🧠 When to Use What

| Use Case | Recommended Interface |
|----------|------------------------|
| Just need username | `Principal` |
| Need user roles, principal, or credentials | `Authentication` |
| Securing APIs with OAuth2 (login via Google, GitHub) | `OAuth2AuthenticationToken` |
| Securing APIs with JWT Bearer tokens (stateless APIs) | `JwtAuthenticationToken` |

---

## 📊 Key Differences

| Feature                            | `Principal` | `Authentication` | `OAuth2AuthenticationToken` | `JwtAuthenticationToken` |
|-----------------------------------|-------------|------------------|------------------------------|---------------------------|
| `getName()` (username)            | ✅          | ✅               | ✅                           | ✅                        |
| Access roles/authorities          | ❌          | ✅               | ✅                           | ✅                        |
| Access user details / claims      | ❌          | ✅ (via `getPrincipal()`) | ✅ (`getPrincipal()`)        | ✅ (`getToken().getClaims()`) |
| JWT-specific claims (email, etc.) | ❌          | ❌               | ❌                           | ✅                        |
| Used in form login / basic auth   | ✅          | ✅               | ❌                           | ❌                        |
| Used in OAuth2 login              | ❌          | ✅               | ✅                           | ❌                        |
| Used in JWT Bearer auth           | ❌          | ✅               | ❌                           | ✅                        |

---

## 🔧 Real-World Examples

### ✅ Using `Principal` (simple)

```java
@GetMapping("/profile")
public String getUser(Principal principal) {
    return "Hello " + principal.getName();
}
```

### ✅ Using `Authentication` (more data)

```java
@GetMapping("/user-info")
public String getUserInfo(Authentication auth) {
    return "User: " + auth.getName() + ", Roles: " + auth.getAuthorities();
}
```

### ✅ Using `OAuth2AuthenticationToken` (social login)

```java
@GetMapping("/oauth2-user")
public String oauthUser(OAuth2AuthenticationToken auth) {
    Map<String, Object> attributes = auth.getPrincipal().getAttributes();
    return "User email: " + attributes.get("email");
}
```

### ✅ Using `JwtAuthenticationToken` (JWT-based API)

```java
@GetMapping("/jwt-user")
public String jwtUser(JwtAuthenticationToken jwtAuth) {
    String email = jwtAuth.getToken().getClaimAsString("email");
    return "Email from JWT: " + email;
}
```

---

## 🧪 TL;DR – Which to Choose?

| If you are using...        | Use this in controller     |
|---------------------------|----------------------------|
| Form login / Basic Auth   | `Authentication` or `Principal` |
| OAuth2 login (Google etc) | `OAuth2AuthenticationToken` |
| JWT API auth              | `JwtAuthenticationToken`    |

---


## working Spring Boot example

**working Spring Boot example** where RSA keys are loaded from `application.yml` into a `record` using `@ConfigurationProperties`.

---

### ✅ Goal

- Load RSA public and private keys from the YAML file.
- Convert them into `RSAPublicKey` and `RSAPrivateKey` objects.
- Inject them using a clean and immutable `record` (`RSAKeyRecord`).

---

## 1️⃣ `application.yml`

```yaml
jwt:
  rsa-public-key: |
    -----BEGIN PUBLIC KEY-----
    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8A...
    -----END PUBLIC KEY-----
  rsa-private-key: |
    -----BEGIN PRIVATE KEY-----
    MIIEvQIBADANBgkqhkiG9w0BAQEFAASCB...
    -----END PRIVATE KEY-----
```

> Make sure these are **actual PEM-formatted keys**, possibly generated with `openssl`.

---

## 2️⃣ `RSAKeyProperties.java`

We can’t use a record *directly* with `@ConfigurationProperties` and also bind raw Strings to `RSAPublicKey`, so we’ll:
- Bind as Strings.
- Convert the keys in a `@PostConstruct` method.

```java
package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@ConfigurationProperties(prefix = "jwt")
public class RSAKeyProperties {

    private String rsaPublicKey;
    private String rsaPrivateKey;

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    @PostConstruct
    public void init() {
        try {
            this.publicKey = loadPublicKey(rsaPublicKey);
            this.privateKey = loadPrivateKey(rsaPrivateKey);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load RSA keys", e);
        }
    }

    private RSAPublicKey loadPublicKey(String key) throws Exception {
        String cleaned = key.replaceAll("-----\\w+ PUBLIC KEY-----", "")
                            .replaceAll("\\s+", "");
        byte[] decoded = Base64.getDecoder().decode(cleaned);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) factory.generatePublic(spec);
    }

    private RSAPrivateKey loadPrivateKey(String key) throws Exception {
        String cleaned = key.replaceAll("-----\\w+ PRIVATE KEY-----", "")
                            .replaceAll("\\s+", "");
        byte[] decoded = Base64.getDecoder().decode(cleaned);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) factory.generatePrivate(spec);
    }

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setRsaPublicKey(String rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }
}
```

---

## 3️⃣ `RSAKeyRecord.java`

Now that we have loaded the keys, you can use an immutable record to **inject and pass** keys where needed:

```java
package com.example.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public record RSAKeyRecord(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
```

---

## 4️⃣ Optional: Provide it as a `@Bean`

In your configuration class:

```java
package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RSAKeyConfig {

    @Bean
    public RSAKeyRecord rsaKeyRecord(RSAKeyProperties props) {
        return new RSAKeyRecord(props.getPublicKey(), props.getPrivateKey());
    }
}
```

Now you can inject `RSAKeyRecord` anywhere in your app:

```java
@Service
public class JwtService {
    private final RSAKeyRecord rsaKeyRecord;

    public JwtService(RSAKeyRecord rsaKeyRecord) {
        this.rsaKeyRecord = rsaKeyRecord;
    }

    // use rsaKeyRecord.publicKey() / privateKey() here
}
```

---

### 🔍 Summary

| Part                     | Purpose |
|--------------------------|---------|
| `@ConfigurationProperties` | Binds YAML values to fields |
| `@PostConstruct`          | Converts raw key strings to actual RSA keys |
| `record`                  | Immutable way to encapsulate keys |
| `@Bean`                   | Makes the record injectable anywhere |

---

## [Filter](https://youtu.be/AzGrVQdIeP0?si=FN39-oz6ovqRLGfM)
- Two types of filter can be used.
  - Implementing `Filter`
  - Extending `OncePerRequestFilter`






# CORS

Both `WebMvcConfigurer` and `FilterRegistrationBean<CorsFilter>` can be used to configure CORS in a Spring Boot application, but they have different use cases and mechanics.

---

## 🔧 `WebMvcConfigurer` (MVC-level CORS)

### ✅ When to Use:
- You’re using **Spring MVC** and want to configure CORS for controller endpoints (`@RestController`, `@Controller`).
- You want Spring to manage the CORS handling internally at the **framework level**.

### 🌟 How it Works:
- Applies CORS mappings before the request reaches the controller.
- Integrated into the Spring Web MVC pipeline.
- It's the **preferred** and most **standard** way for CORS in most Spring Boot apps.

### Example:
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
```

---

## 🔧 `FilterRegistrationBean<CorsFilter>` (Servlet filter-level CORS)

### ✅ When to Use:
- You need **more control**, e.g. for **non-MVC endpoints** like WebFlux, or filters before Spring MVC (e.g., security filters, raw servlets).
- You want to configure CORS **before** any Spring security filters (e.g., for token validation, login endpoints).
- You're doing **custom CORS handling** or using **Spring Security + CORS** in a complex way.

### 🌟 How it Works:
- Registers a `CorsFilter` at the **servlet container level**, independent of Spring MVC.
- More **low-level**, applies **before** the request even reaches the DispatcherServlet.

### Example:
```java
@Bean
public FilterRegistrationBean<CorsFilter> corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("http://localhost:3000");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);

    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
    bean.setOrder(0); // Make sure this filter runs early
    return bean;
}
```

---

## 🆚 Summary: Difference Between the Two

| Feature                      | `WebMvcConfigurer`            | `FilterRegistrationBean<CorsFilter>`     |
|-----------------------------|-------------------------------|------------------------------------------|
| Applies To                  | Spring MVC controllers        | All incoming requests (servlet-level)    |
| Position in Filter Chain    | After Spring Security (by default) | Before everything (if order is set)   |
| Best For                    | REST APIs (standard use case) | Complex setups, custom filters, raw servlets |
| Integration Level           | High-level (MVC)              | Low-level (Servlet API)                  |
| Configuration Simplicity    | Simple                        | More verbose                             |

---

## 🔥 When to Choose What?

- Use **`WebMvcConfigurer`** for **most REST APIs** — it’s clean and Spring-idiomatic.
- Use **`FilterRegistrationBean<CorsFilter>`** if:
  - You're integrating with **Spring Security** manually.
  - You need **CORS before authentication**.
  - You're building **non-MVC** endpoints (e.g., WebSocket, raw servlets).

---



































































### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.4/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.4/gradle-plugin/packaging-oci-image.html)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/3.4.4/specification/configuration-metadata/annotation-processor.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.4/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.4/reference/using/devtools.html)
* [OAuth2 Resource Server](https://docs.spring.io/spring-boot/3.4.4/reference/web/spring-security.html#web.security.oauth2.server)
* [Spring Security](https://docs.spring.io/spring-boot/3.4.4/reference/web/spring-security.html)
* [Validation](https://docs.spring.io/spring-boot/3.4.4/reference/io/validation.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.4/reference/web/servlet.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

