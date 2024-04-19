package com.tpkhanh.chatappapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Chỉ định nguồn được phép truy cập
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Chỉ định các phương thức HTTP được phép
                .allowedHeaders("*"); // Chỉ định các headers được phép
    }
}
