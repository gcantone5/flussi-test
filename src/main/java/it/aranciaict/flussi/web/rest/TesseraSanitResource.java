package it.aranciaict.flussi.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.aranciaict.flussi.model.dto.TesseraSanitDTO;
import it.aranciaict.flussi.service.TesseraSanitService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/tesserasanit")
public class TesseraSanitResource {

	@Autowired
	private TesseraSanitService tesseraSanitService;

	@GetMapping("")
	public ResponseEntity<List<TesseraSanitDTO>> findAll() {
		log.debug("get all tesserasanit");
		List<TesseraSanitDTO> allTesseraSanit = tesseraSanitService.getAllTesseraSanit();
		return ResponseEntity.ok(allTesseraSanit);
	}

	@GetMapping("/{cf}")
	public ResponseEntity<TesseraSanitDTO> findOneByCodiceFiscale(@PathVariable String cf) {
		log.debug("get all tesserasanit");
		TesseraSanitDTO tesseraSanit = tesseraSanitService.getOneByCodiceFiscaleEquals(cf);
		return ResponseEntity.ok(tesseraSanit);
	}

	@GetMapping("/validate")
	public ResponseEntity<List<TesseraSanitDTO>> validateAll() {
		log.debug("get all tesserasanit");
		List<TesseraSanitDTO> allTesseraSanit = tesseraSanitService.validateTessereSanit();
		return ResponseEntity.ok(allTesseraSanit);
	}

	@GetMapping("/ko")
	public ResponseEntity<List<TesseraSanitDTO>> findAllWithKO() {
		log.debug("get all tesserasanit KO");
		List<TesseraSanitDTO> allTesseraSanitKO = tesseraSanitService.getAllTesseraSanitKO();
		return ResponseEntity.ok(allTesseraSanitKO);
	}
	
	@GetMapping("/flusso/{flussoId}")
	public ResponseEntity<List<TesseraSanitDTO>> findAllByFlussoId(@PathVariable String flussoId) {
		log.debug("get all tesserasanit KO");
		List<TesseraSanitDTO> allTesseraSanitKO = tesseraSanitService.getAllTesseraSanitKO();
		return ResponseEntity.ok(allTesseraSanitKO);
	}

}
