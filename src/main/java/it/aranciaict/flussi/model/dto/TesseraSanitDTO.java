package it.aranciaict.flussi.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class TesseraSanitDTO {
	
	private String id;

	private String val;

	private String codiceFiscale;
	
	private String f24;
	
	private List<String> qa;
	
	private String flussoId;
	
}
