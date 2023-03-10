package br.akd.svc.akadia.config.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.akd.svc.akadia"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        return new ApiInfo(
                "Akadia Solutions",
                "Back end do ecosistema Akadia",
                "1.0.0",
                "Terms of Service",
                new Contact("Gabriel Lagrota",
                        "https://github.com/AkadiaSolutions/akd-svc-saas-erp",
                        "gabriellagrota23@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licensen.html",
                new ArrayList<>()
        );
    }

}
