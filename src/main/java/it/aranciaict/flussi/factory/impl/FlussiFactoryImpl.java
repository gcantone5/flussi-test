package it.aranciaict.flussi.factory.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import it.aranciaict.flussi.factory.FlussiFactory;

@Component
public class FlussiFactoryImpl implements FlussiFactory {
	
	@Autowired()
	@Qualifier(value = "collectionsMapping")
	private Map<String, String> collectionsMapping;
	
	@Override
	public String getCollectionName(String nomeFlusso) {
		return collectionsMapping.get(nomeFlusso);
	}

}
