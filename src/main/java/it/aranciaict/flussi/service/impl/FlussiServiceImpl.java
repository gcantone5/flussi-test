package it.aranciaict.flussi.service.impl;

import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;

import it.aranciaict.flussi.factory.FlussiFactory;
import it.aranciaict.flussi.model.Flusso;
import it.aranciaict.flussi.model.response.FlussoExportResponse;
import it.aranciaict.flussi.model.response.FlussoWorkResponse;
import it.aranciaict.flussi.service.FlussiService;
import it.aranciaict.flussi.service.FlussoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FlussiServiceImpl implements FlussiService {

	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	private FlussiFactory flussiFactory;

	@Autowired
	public ObjectMapper mapper;

	@Autowired
	public FlussoService flussoService;

	@Override
	public List<BasicDBObject> getAllElement(String nomeFlusso) {
		log.debug("get All Element for flusso {}", nomeFlusso);
		String collectionName = flussiFactory.getCollectionName(nomeFlusso);
		List<BasicDBObject> documents = mongoOperations.findAll(BasicDBObject.class, collectionName);
		return documents;
	}

	@Override
	public BasicDBObject insertElement(String nomeFlusso, HashMap<String, Object> params) {
		log.debug("insert element for flusso {0} with params {1}", nomeFlusso, params);
		BasicDBObject doc = new BasicDBObject(params);
		parseObjectId(doc);
		String collectionName = flussiFactory.getCollectionName(nomeFlusso);
		doc = mongoOperations.insert(doc, collectionName);
		return doc;
	}

	@Override
	public List<BasicDBObject> readExport(String nomeFlusso, String fileName) {
		log.debug("insert file {1} for flusso {0}", nomeFlusso);
		Flusso flusso = null;
		FlussoExportResponse response = null;
		try {
			flusso = flussoService.create(nomeFlusso);
			FileInputStream inputStream = new FileInputStream(fileName);
			response = mapper.readValue(inputStream, new TypeReference<FlussoExportResponse>() {
			});
			if (!org.springframework.util.CollectionUtils.isEmpty(response.getDoc())) {
				for (BasicDBObject doc : response.getDoc()) {
					parseObjectId(doc);
					setFlusso(doc, flusso);
					String collectionName = flussiFactory.getCollectionName(nomeFlusso);
					doc = mongoOperations.insert(doc, collectionName);
				}
			}
		} catch (Exception e) {
			onImportError(flusso, e);
			log.error(e.getMessage(), e);

		}
		onImportFinished(flusso);
		return response.getDoc();
	}

	@Override
	public BasicDBObject readWork(String nomeFlusso, String fileName) {
		log.debug("insert file {1} for flusso {0}", nomeFlusso);
		Flusso flusso = null;
		FlussoWorkResponse response = null;
		try {
			flusso = flussoService.create(nomeFlusso);
			FileInputStream inputStream = new FileInputStream(fileName);
			response = mapper.readValue(inputStream, new TypeReference<FlussoWorkResponse>() {
			});
			String collectionName = flussiFactory.getCollectionName(nomeFlusso);
			parseObjectId(response.getDoc());
			setFlusso(response.getDoc(), flusso);
			mongoOperations.insert(response.getDoc(), collectionName);
		} catch (Exception e) {
			onImportError(flusso, e);
			log.error(e.getMessage(), e);
		}
		onImportFinished(flusso);
		return response.getDoc();
	}

	private void parseObjectId(BasicDBObject doc) {
		if (doc.containsField("_id")) {
			String id = (String) doc.get("_id");
			doc.put("_id", new ObjectId(id));
		}
	}

	private void setFlusso(BasicDBObject doc, Flusso flusso) {
		doc.put("flusso_id", new ObjectId(flusso.getId()));
	}

	private void onImportError(Flusso flusso, Throwable e) {
		if (flusso != null) {
			flusso.setErrorMessage(e.getMessage());
			flusso.setException(ExceptionUtils.getStackTrace(e));
			flussoService.save(flusso);
		}
	}

	private void onImportFinished(Flusso flusso) {
		if (flusso != null) {
			flusso.setFinishDate(new Date());
			flussoService.save(flusso);
		}
	}

}
