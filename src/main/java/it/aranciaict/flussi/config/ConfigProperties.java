package it.aranciaict.flussi.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "flussi")
public class ConfigProperties {

	@Bean(name = "collectionsMapping")
	public Map<String, String> collections() {
		Map<String, String> collections =  new HashMap<String, String>();
		collections.put("farmaci", "flusso_farmaci");
		collections.put("aziende", "flusso_aziende");
		collections.put("tesserasanit", "tesserasanit");
		collections.put("fattelettron", "fattelettron");
		return collections;
	}

}
