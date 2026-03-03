package com.expedienteclinico.expedienteclinico;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//Agregaciones para swagger

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;

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

	@Bean
	public OpenAPI clinicaOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("API de Expediente Médico - Clínica")
						.description("Sistema para la gestión integral de pacientes, inventario de farmacia y consultas médicas.")
						.version("1.0.0")
						.contact(new Contact()
								.name("Yisus")
								.email("lazarochucho100@gmail.com")));
	}

}
