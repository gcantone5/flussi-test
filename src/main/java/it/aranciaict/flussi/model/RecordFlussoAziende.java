package it.aranciaict.flussi.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.BasicDBObject;

@Document(collection = "flusso_aziende")
public class RecordFlussoAziende extends BasicDBObject{

	@Id
	private String _id;
	
	public RecordFlussoAziende(HashMap<String, Object> params) {
		super(params);
	}
	
}
