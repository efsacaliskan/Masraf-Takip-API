package com.innova.masraf_takip_api.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Masraf Takip API")
                        .version("1.0")
                        .description("Masraf Takip API dokümantasyonu")
                        .contact(new Contact()
                                .name("Efsa Çalışkan")
                                .email("efsa.caliskan@ozu.edu.tr") )); 
    }
}
