package it.aranciaict.flussi.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "flusso")
public class Flusso {

	@Id
	private String id;

	private String name;

	@CreatedDate
	private Date createdDate;

	private Date finishDate;

	private Date validationDate;
	
	private String errorMessage;
	
	private String exception;
	

}
