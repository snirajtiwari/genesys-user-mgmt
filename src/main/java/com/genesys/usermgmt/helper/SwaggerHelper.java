package com.genesys.usermgmt.helper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.genesys.usermgmt.constant.SwaggerConstants;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerHelper.
 */
@Configuration
@EnableSwagger2
public class SwaggerHelper {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.genesys.usermgmt.controller")).paths(PathSelectors.any())
				.build().apiInfo(apiEndPointsInfo())
				.tags(new Tag(SwaggerConstants.USER_TAG, SwaggerConstants.USER_DESCRIPTION));
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("Genesys Corporation").version("1.0").description("User API Details").build();
	}
}
