package com.self.base.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 添加Swagger服务
     * 访问地址：http://[domain]/swagger-ui.html
     * @return Docket对象
     */
    @Bean
    public Docket swaggerService(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.self.base.app"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 接口文档信息
     * @return 接口文档信息
     */
    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("RESTful 接口文档")
                .description("API描述")
                .version("1.0")
                .contact(new Contact("SpringBoot", "", ""))
                .build();
    }

}
