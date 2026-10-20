package ma.projet.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ma.projet")
public class AppConfig {
    // Cette classe centralise le balayage des composants Spring
}
