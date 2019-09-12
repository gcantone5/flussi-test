package it.aranciaict.flussi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import it.aranciaict.flussi.config.ConfigProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class MongodbFlussiTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodbFlussiTestApplication.class, args);
	}

}
