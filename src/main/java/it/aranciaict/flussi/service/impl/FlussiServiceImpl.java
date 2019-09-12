package it.aranciaict.flussi.service.impl;

import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

import it.aranciaict.flussi.factory.FlussiFactory;
import it.aranciaict.flussi.service.FlussiService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FlussiServiceImpl implements FlussiService {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private FlussiFactory flussiFactory;
	
	@Override
	public List<BasicDBObject> getAllElement(String nomeFlusso) {
		log.debug("get All Element for flusso {}", nomeFlusso);
		String collectionName = flussiFactory.getCollectionName(nomeFlusso);
		List<BasicDBObject> documents = mongoTemplate.findAll(BasicDBObject.class , collectionName);
		return documents;
	}
	
	
	

	@Override
	public BasicDBObject insertElement(String nomeFlusso, HashMap<String, Object> params) {
		log.debug("insert element for flusso {0} with params {1}", nomeFlusso, params);
		BasicDBObject doc = new BasicDBObject(params);
		String collectionName = flussiFactory.getCollectionName(nomeFlusso);
		doc = mongoTemplate.insert(doc, collectionName);
		return doc;
	}
	
	
	
	
	
}
