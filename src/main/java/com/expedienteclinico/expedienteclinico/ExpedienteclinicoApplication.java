package com.expedienteclinico.expedienteclinico;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Date;
import java.util.TimeZone;

@EnableWebMvc
@SpringBootApplication
public class ExpedienteclinicoApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Override
	protected SpringApplicationBuilder configure( SpringApplicationBuilder application ) {
		return application.sources( ExpedienteclinicoApplication.class ) ;
	}

	public void init() {
		TimeZone.setDefault( TimeZone.getTimeZone( "UTC" ) ) ;
		System.out.println( "SpringBoot Application running in UTC timezone: "+ new Date() ) ;
	}

	public static void main(String[] args) {
		SpringApplication.run(ExpedienteclinicoApplication.class, args);
	}

	@Override
	public void run( String... args ) throws Exception {
		System.out.println( "---------------------" ) ;
		System.out.println( "..:: Initialized ::.." ) ;
		System.out.println( "---------------------" ) ;
	}

}
