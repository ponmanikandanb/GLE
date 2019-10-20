package com.temenos.gle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.temenos.gle.config.AppConfig;
import com.temenos.gle.dao.GlobalLiquidEngineDAO;
import com.temenos.gle.data.ProcessT24Data;

@SpringBootApplication
public class StartGLEApplication {
	
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		GlobalLiquidEngineDAO gleDAO = context.getBean(GlobalLiquidEngineDAO.class);
		// Feed the data
		gleDAO.insertIntoCodexData(ProcessT24Data.getData());
		// Get the balance
		System.out.println("Balance of 77507 :"+ gleDAO.getBalance("77507", "BNK"));
		SpringApplication.run(StartGLEApplication.class, args);
    }
}