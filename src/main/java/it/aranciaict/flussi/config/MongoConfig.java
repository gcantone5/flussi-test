package it.aranciaict.flussi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



@Configuration
@EnableMongoRepositories(basePackages = "it.aranciaict.flussi.repository")
@EnableMongoAuditing
public class MongoConfig { 
	
//	extends AbstractMongoConfiguration {
//
//
//	@Override
//	protected String getDatabaseName() {
//		return "warehouse";
//	}
//
//	@Override
//	public MongoClient mongoClient() {
//		return new MongoClient("mongodb://localhost", 27017);
//	}
//
//	@Override
//	protected String getMappingBasePackage() {
//		return "com.example.mongodb.model";
//	}
	
}
