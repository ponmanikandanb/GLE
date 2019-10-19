package com.temenos.gle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class StartGLEApplication {

    // start everything
    public static void main(String[] args) {
    	ApplicationContext context = 
        		new ClassPathXmlApplicationContext("gle-spring-context.xml");
        SpringApplication.run(StartGLEApplication.class, args);
    }
    
}