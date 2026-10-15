package at.spengergasse.spengerbite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

// SpringbootApplication is a convenience annotation that combines the following annotations:

// @SpringBootConfiguration
// Indicates that this class provides Spring Boot application configuration
//   (e.g. @Bean methods, @Configuration classes, etc.)

// @EnableAutoConfiguration
// Configures Spring Boot based on the dependencies present in the classpath
//   (e.g. if spring-webmvc is on the classpath, it will configure a Tomcat Web server)

// @ComponentScan
// Scans the package of the annotated class for Spring components
//   (e.g., @Component, @Service, @Repository, @Controller, etc.) and registers them as beans in the Spring application context.

// All this happens at boot time, when the application is started up.

@SpringBootApplication
public class SpengerbiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpengerbiteApplication.class, args);
	}

}
