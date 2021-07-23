package com.gmc.gmccoin.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@Controller
@Profile({"local","dev"})
public class SwaggerConfig {
    @GetMapping("/")
    public String index() {
        return "redirect:swagger-ui.html";
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket apiDocketForStores() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("GMC COIN")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gmc.gmccoin.api"))
                .paths(PathSelectors.regex("/*.*"))
                .build()
                .apiInfo(getApiInfo())
                ;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "GMCcoin API",
                "GMC",
                "0.0.1",
                "/",
                new Contact("GMC", "", ""),
                null,
                null,
                Collections.emptyList()
        );
    }
}