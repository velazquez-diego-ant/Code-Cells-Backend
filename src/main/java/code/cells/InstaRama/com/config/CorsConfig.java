package code.cells.InstaRama.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("Origin", "Content-Type", "Authorization", "Accept", "X-Requested-With")
                        .exposedHeaders("Content-Length")
                        .allowCredentials(true)
                        .maxAge(12 * 60 * 60)
                        .allowedOriginPatterns(
                                // --- LOCALHOST ---
                                "http://localhost",
                                "http://localhost:[*]",
                                
                                // --- 127.0.0.1 por si acaso ---
                                "http://127.0.0.1",
                                "http://127.0.0.1:[*]" 
                                
                                // --- SERVIDOR DE PRODUCCIÓN (dejar vacío por ahora) ---
                                // "http://24.199.98.8",
                                // "http://24.199.98.8:[*]",
                                
                        );
            }
        };
    }
}