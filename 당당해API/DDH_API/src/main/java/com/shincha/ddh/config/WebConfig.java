package com.shincha.ddh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import com.shincha.ddh.interceptor.JwtInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

		registry.addResourceHandler("/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
	}

//	@Autowired
//	private JwtInterceptor jwtInterceptor;

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(jwtInterceptor)
//		.addPathPatterns("/**")
//		.excludePathPatterns("/api-user/login","/api-user/registUser",
//				"/swagger-resources/**",
//				"/swagger-ui/**",
//				"/v2/api-docs");
//	}

	// CORS 에러를 해결하기 위해서 컨트롤러에서 세분화 하여 처리할 수도 있지만
	// 전역설정처럼 여기서 한방에 처리할 수도 있음..
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//cors 떠서 추가로 allow 요소 추가함
		//https://velog.io/@wnguswn7/CORS-%EC%97%90%EB%9F%AC-Access-to-XMLHttpRequest-at-from-origin-has-been-blocked-by-CORS-policy
		registry.addMapping("/**").allowedOrigins("*").allowedOriginPatterns("*") // 안에 해당 주소를 넣어도 됨
				.allowedHeaders("*").allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS", "PATCH")
				.exposedHeaders("Authorization", "RefreshToken");// .allowedMethods("GET", "POST");
	}
}
