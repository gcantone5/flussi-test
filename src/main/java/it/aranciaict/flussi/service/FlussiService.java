package it.aranciaict.flussi.service;

import java.util.HashMap;
import java.util.List;

import com.mongodb.BasicDBObject;

public interface FlussiService {

	List<BasicDBObject> getAllElement(String nomeFlusso);

	BasicDBObject insertElement(String nomeFlusso, HashMap<String, Object> params);

	List<BasicDBObject> readExport(String nomeFlusso, String fileName);
	
	BasicDBObject readWork(String nomeFlusso, String fileName);

}
