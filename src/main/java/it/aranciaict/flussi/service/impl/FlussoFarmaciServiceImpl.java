package it.aranciaict.flussi.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import it.aranciaict.flussi.model.RecordFlussoFarmaci;
import it.aranciaict.flussi.service.FlussoFarmaciService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Qualifier(value ="flussoFarmaciService")
public class FlussoFarmaciServiceImpl implements FlussoFarmaciService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<RecordFlussoFarmaci> findAll() {
		log.debug("get All Element");
		List<RecordFlussoFarmaci> documents = mongoTemplate.findAll(RecordFlussoFarmaci.class);
		return documents;
	}

	@Override
	public RecordFlussoFarmaci insert( HashMap<String, Object> params) {
		log.debug("insert element with params {1}", params);
		RecordFlussoFarmaci doc = new RecordFlussoFarmaci(params);
		doc = mongoTemplate.insert(doc);
		return doc;
	}
}
