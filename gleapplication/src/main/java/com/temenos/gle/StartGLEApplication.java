package com.temenos.gle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.temenos.gle.config.AppConfig;
import com.temenos.gle.dao.GlobalLiquidEngineDAO;

@SpringBootApplication
public class StartGLEApplication {
	static AnnotationConfigApplicationContext  context;
    // start everything
    public static void main(String[] args) {
		/*
		 * ApplicationContext context = new
		 * ClassPathXmlApplicationContext("gle-spring-context.xml");
		 * SpringApplication.run(StartGLEApplication.class, args);
		 */
    	
        context = new AnnotationConfigApplicationContext(AppConfig.class);
		GlobalLiquidEngineDAO gleDAO = context.getBean(GlobalLiquidEngineDAO.class);
		System.out.println(gleDAO.getAll());
		SpringApplication.run(StartGLEApplication.class, args);
    }
    
    public static ApplicationContext getApplicationContext() {
        return context;
    }
    
}