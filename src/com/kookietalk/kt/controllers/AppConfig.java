package com.kookietalk.kt.controllers;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Configuration
@PropertySource("classpath:/com/kookietalk/kt/controllers/anet.properties")
public class AppConfig {

	@Value("${apiloginid}")
	private String apiLoginId;
	
	@Value("${transactionkey}")
	private String transactionKey;
	
	@Value("${environment}")
	private String enviornment;
	
	@Bean
	public Properties properties() {
		Properties props = new Properties();
		props.setProperty("apiLoginId", apiLoginId);
		props.setProperty("transactionKey", transactionKey);
		props.setProperty("environment", enviornment);
		return props;
	}
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
