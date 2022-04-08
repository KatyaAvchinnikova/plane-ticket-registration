package com.innowise.businesslayer.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "My API", version = "v1"))
@SecurityScheme(name = "basicAuth",
                type = SecuritySchemeType.HTTP,
                scheme = "basic")
public class SwaggerConfig {
    private static final String SCHEME_NAME = "bearerScheme";
    private static final String SCHEME = "basic";

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                             .group("public")
                             .pathsToMatch("/api/**")
                             .build();
    }


//   private ApiKey apiKey() {
//        return new ApiKey("JWT", "Authorization", "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                              .securityReferences(defaultAuth())
//                              .build();
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        return List.of(new SecurityReference("JWT", new AuthorizationScope[]{authorizationScope}));
//    }
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .securityContexts(List.of(securityContext()))
//                .securitySchemes(List.of(apiKey()))
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().build();
//    }
}
