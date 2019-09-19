package it.aranciaict.flussi.web.rest;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;

import it.aranciaict.flussi.model.Flusso;
import it.aranciaict.flussi.service.FlussiService;
import it.aranciaict.flussi.service.FlussoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/flussi")
public class FlussiResource {

	@Autowired
	private FlussiService flussiService;
	
	@Autowired
	private FlussoService flussoService;

	private static final String EXPORT_PATH = "C:\\Users\\Giuseppe\\Documents\\Arancia\\Progetti\\SpendingPHA\\dataExport\\export_ok\\";
	private static final String WORK_PATH = "C:\\Users\\Giuseppe\\Documents\\Arancia\\Progetti\\SpendingPHA\\dataExport\\work_ok\\";
	
	@GetMapping("")
	public ResponseEntity<List<Flusso>> findAll() {
		log.debug("get all flussi");
		List<Flusso> documents = flussoService.findAll();
		return ResponseEntity.ok(documents);
	}
	
	@GetMapping("/{nomeFlusso}")
	public ResponseEntity<List<BasicDBObject>> findAll(@PathVariable String nomeFlusso) {
		log.debug("get all elements for flusso {}", nomeFlusso);
		List<BasicDBObject> documents = flussiService.getAllElement(nomeFlusso);
		return ResponseEntity.ok(documents);
	}

	@PostMapping("/{nomeFlusso}")
	public ResponseEntity<BasicDBObject> insertDocument(@PathVariable String nomeFlusso,
			@RequestBody HashMap<String, Object> params) {
		log.debug("insert element for flusso {0} with params {1} ", nomeFlusso, params);
		BasicDBObject document = flussiService.insertElement(nomeFlusso, params);
		return ResponseEntity.ok(document);
	}

	@PostMapping("/import/export/{nomeFlusso}")
	public ResponseEntity<List<BasicDBObject>> readExport(@PathVariable String nomeFlusso) {
		log.debug("insert elements EXPORT for flusso {0}", nomeFlusso);
		String fileName = EXPORT_PATH + "output2.txt";
		List<BasicDBObject> documents = flussiService.readExport(nomeFlusso, fileName);
		return ResponseEntity.ok(documents);
	}

	@PostMapping("/import/work/{nomeFlusso}")
	public ResponseEntity<BasicDBObject> readWork(@PathVariable String nomeFlusso) {
		log.debug("insert elements WORK for flusso {0}", nomeFlusso);
		String fileName = WORK_PATH + "output2.txt";
		BasicDBObject document = flussiService.readWork(nomeFlusso, fileName);
		return ResponseEntity.ok(document);
	}

}
