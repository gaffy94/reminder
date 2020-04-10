package com.gaf.reminderserver;

import com.gaf.reminderserver.configs.CorsFilter;
import com.gaf.reminderserver.configs.SpringSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
public class ReminderserverApplication {
	@Autowired
	Environment env;

	public static void main(String[] args) {
		SpringApplication.run(ReminderserverApplication.class, args);
	}

	@Bean
	public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
		return new SpringSecurityConfig();
	}

	@Bean
	public FilterRegistrationBean corsFilterRegistration(){
		FilterRegistrationBean registrationBean =
				new FilterRegistrationBean(new CorsFilter());
		registrationBean.setName("CORS FILTER");
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(1);
		return registrationBean;

	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder
				.setConnectTimeout(Duration.ofSeconds(Long.parseLong(env.getProperty("rest.connect.timeout"))))
				.setReadTimeout(Duration.ofSeconds(Long.parseLong(env.getProperty("rest.read.timeout"))))
				.build();
	}

}
