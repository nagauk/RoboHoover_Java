package com.yoti.hoover.RoboHoover.rest.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Arrays;
/**
 * *Author  : Nagaraju Ukkalam
 * *Date    : 5/20/2019
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /***
     *
     * @return Docket
     *
     * Enabling swagger
     */
    @Bean
    public Docket docketApiDetails() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("yoti-robo-hoover-api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yoti.hoover.RoboHoover.rest.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(new ResponseMessageBuilder()
                        .code(500)
                        .message("500 Internal server error.....")
                        .responseModel(new ModelRef("Error"))
                        .build()))
                .apiInfo(apiInfoDetails());
    }

    private ApiInfo apiInfoDetails() {
        return new ApiInfoBuilder()
                .title("YOTI ROBO HOOVER DEMO API DOCUMENTATION")
                .version("1.0")
                .license("MIT")
                .description("Yoti RoboHoover clean the dirt patches which are provided as input...")
                .build();
    }

}
