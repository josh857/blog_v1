package com.portal.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(

        info = @Info(
                title = "josh部落格swagger",
                description = "",
                version = "1.0.0",
                contact = @Contact(),
                license = @License()
        ),
        servers = {
                @Server(),
                @Server()
        },
        security = @SecurityRequirement(name = "Oauth2"),
        externalDocs = @ExternalDocumentation(
                description = "",
                url = ""
        )
)
@Configuration
public class SwaggerConfigurer {

        /**
         * 配置分組swagger
         * group :自行定義
         * pathsToMatch : 選擇接口路徑
         */
        @Bean
        public GroupedOpenApi tempApi(){
            return GroupedOpenApi.builder()
                    .group("blog")
                    .pathsToMatch("/v1/Blog/**")
                    .build();
        }
}
