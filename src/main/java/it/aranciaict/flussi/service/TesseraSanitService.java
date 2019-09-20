package it.aranciaict.flussi.service;

import java.util.List;

import it.aranciaict.flussi.model.dto.TesseraSanitDTO;

public interface TesseraSanitService {

	List<TesseraSanitDTO> getAllTesseraSanit();
	
	TesseraSanitDTO getOneByCodiceFiscaleEquals(String codiceFiscale);
	
	List<TesseraSanitDTO> validateTessereSanit();

	List<TesseraSanitDTO> getAllTesseraSanitKO();

	List<TesseraSanitDTO> getAllByFlussoId(String flussoId);
	
}
