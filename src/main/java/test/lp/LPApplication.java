package test.lp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collections;

/**
 * This class serves as an entry point for the Spring Boot app.
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EntityScan(basePackages = "com.lp.domain")
@EnableJpaRepositories(basePackages = "com.lp.domain")
@ComponentScan(basePackages = {
        "com.lp.web",
        "com.lp.domain"
})
@OpenAPIDefinition(servers = {
        @Server(url = "/", description = "Default Server URL")
})
public class LPApplication {

    private static final Logger logger = LoggerFactory.getLogger(LPApplication.class);

    public static void main(final String[] args) {
        String port = System.getenv("PORT");
        if (port == null) {
            port = "8080";
            logger.warn("$PORT environment variable not set, defaulting to 8080");
        }
        SpringApplication app = new SpringApplication(LPApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", port));

        // Start the Spring Boot application.
        app.run(args);

        logger.info(
                "Hello from Cloud Run! The container started successfully and is listening for HTTP requests on " + port);
    }
}
