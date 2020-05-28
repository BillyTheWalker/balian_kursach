package com.example.demo;

import com.example.demo.config.SetUpDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(scanBasePackages = "com.example.*")
@EnableSwagger2
public class DemoApplication
{

	public static void main(String[] args)
	{
		final ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		SetUpDB setUpDB = context.getBean(SetUpDB.class);
		setUpDB.initDB();
	}

}
