# sps-java-shared-spring-boot-example
An example project that uses spring boot with the sps-java-shared library.

See also:
- [sps-java-shared feature documentation](https://github.com/SPSCommerce/sps-java-shared/tree/main/docs)

## Requirements
- JDK installed at the version described in the `pom.xml`.
- A [settings.xml](https://atlassian.spscommerce.com/wiki/pages/viewpage.action?spaceKey=SIP&title=SIP30%3A+Package+Management#SIP30:PackageManagement-SPSPackageRegistries) with your artifactory credentials located in your `~/.m2` directory.

## Building and Running the App
When the app runs, it provides a small example response at `http://localhost:8080`.

### Compiling and Running Tests
If there is an available JDK, the project can be compiled with the maven wrapper:
```
./mvnw clean package
```
This will build a `sps-java-shared-spring-boot-example.jar` file and dependency folder `libs` in `./target` directory.

### Run in Intellij
There is a provided intellij run configuration in `./run` directory.

### Run in Docker
An example of building a runnable docker image can be seen in the `runDockerLocal.sh` script.

## Spring Properties
When using the spring packages from sps-java-shared, you will need two property files in your classpath:
- `bootstrap.properties`
- `application.properties`

### bootstrap.properties
The core spring module uses spring-cloud bootstrap to "preConfig" the application properties.  This provides
information for the `ProviderEnvironment` and is what enables role assumption and secret lookups.  It will
be loaded as part of the `@EnableAutoConfiguration` scan from `@SpringBootApplication`.

At minimum needs `sps.env` defined, which should be set as the environment variable value.

### application.properties
This is your typical spring properties file. This is used to set various things and is available to your application.
Properties created during the bootstrap are injected into the `application.properties`.
```
test.prop=value
test.prop2=${sps.env}
test.prop3=${my/secret}
```

## Importing Modules
The sps-java-shared library provides some modules that you can easily import to your own spring project.
To do this we recommend using the `@Import` annotation for any of the added `@Configuration` classes.
This will merge all the configurations and will enable beans and properties for those configs in the spring context.
```java
@Configuration
@Import({SpringWeblogicConfig.class})
public class Config {}
```

## Testing the App
We recommend tests use the standard `spring-boot-starter-test` dependency and utilize `Junit5`.

Example tests can be found in `./src/test/java`.
