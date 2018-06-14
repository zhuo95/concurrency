package com.zz.concurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ConcurrencyApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(ConcurrencyApplication.class, args);
	}

	//加入interceptor
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
	}

	//设置加入filter
	@Bean
	public FilterRegistrationBean httpFilter(){
		FilterRegistrationBean registrationBean =  new FilterRegistrationBean();
		//设置filter
		registrationBean.setFilter(new HttpFilter());
		//定义拦截的url
		registrationBean.addUrlPatterns("/threadLocal/*");

		return registrationBean;
	}
}
