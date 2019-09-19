package it.aranciaict.flussi.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "tesserasanit")
public class TesseraSanit {

	@Id
	private String id;

	private String val;

	@NotBlank
	@Size(min = 16, max = 16, message = "Il codice Fiscale deve avere lunghezza 17")
	@Field(value = "content.fileA.f48")
	private String codiceFiscale;
	
	@NotBlank(message = "f24 obbligatorio")
	private String f24;
	
	// validation violations
	private List<String> qa = new ArrayList<>();
	
	@Field(value = "flusso_id")
	private String flussoId;
	

	public void addViolation(String message) {
		this.qa.add(message);
	}
	
	public void resetViolations() {
		this.qa.clear();
	}


}
