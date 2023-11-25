package com.github.youssfbr.gvendas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("users")
                .pathsToMatch("/categories/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {

        final Contact contact = new Contact();
        contact.setName("Alisson Youssf");
        contact.setEmail("youssfbr@gmail.com");
        contact.setUrl("https://github.com/youssfbr");

        return new OpenAPI()
                .info(new Info()
                        .title("API Gestão de Vendas")
                        .description("Sistema de Gestão de Vendas")
                        .contact(contact)
                        .version("1.0.0")
                );
    }

}
