package it.aranciaict.flussi.service;

import java.util.HashMap;
import java.util.List;

import it.aranciaict.flussi.model.RecordFlussoFarmaci;

public interface FlussoFarmaciService {

	List<RecordFlussoFarmaci> findAll();

	RecordFlussoFarmaci insert(HashMap<String, Object> params);

}
