package com.yeeee.crowdfunding.config.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Swagger API文档相关配置
 */
@Configuration
@EnableSwagger2WebMvc
@Profile({"dev", "test"})
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.yeeee.crowdfunding.controller")
                .title("众筹平台")
                .description("相关接口文档")
                .contactName("yeeee")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
