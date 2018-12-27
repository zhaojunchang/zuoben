package com.zuoben.gate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	 @Bean
	    public Docket createRestApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo());
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("【ZUOBEN】管理系统")
	                .description("【ZUOBEN】管理系统接口文档说明")
	                .termsOfServiceUrl("http://localhost:8762")
	                .contact("swagger")
	                .version("2.0")
	                .build();
	    }

	    @Bean
	    UiConfiguration uiConfig() {
	        return new UiConfiguration(null, "list", "alpha", "schema",
	                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
	    }
}



