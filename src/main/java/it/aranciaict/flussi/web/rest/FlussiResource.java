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

import it.aranciaict.flussi.service.FlussiService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/flussi")
public class FlussiResource {

	@Autowired
	private FlussiService flussiService;

//	@Autowired
//	private FlussoFarmaciService flussoFarmaciService;

	@GetMapping("/{nomeFlusso}")
	public ResponseEntity<List<BasicDBObject>> findAll(@PathVariable String nomeFlusso){
		log.debug("get all elements for flusso {}", nomeFlusso);
		List<BasicDBObject> documents = flussiService.getAllElement(nomeFlusso);
		return ResponseEntity.ok(documents);
	}
	
	@PostMapping("/{nomeFlusso}")
	public ResponseEntity<BasicDBObject> insertDocument(@PathVariable String nomeFlusso, @RequestBody HashMap<String, Object> params){
		log.debug("insert element for flusso {0} with params {1} ", nomeFlusso, params);
		BasicDBObject document = flussiService.insertElement(nomeFlusso, params);
		return ResponseEntity.ok(document);
	}

//	@GetMapping("/{nomeFlusso}")
//	public ResponseEntity<List<BasicDBObject>> findAll(@PathVariable String nomeFlusso) {
//		log.debug("get all elements for flusso {}", nomeFlusso);
//		List<BasicDBObject> documents;
//		if ("farmaci".equalsIgnoreCase(nomeFlusso)) {
//			documents = flussoFarmaciService.findAll();
//		} else {
//			documents = flussiService.getAllElement(nomeFlusso);
//		}
//		return ResponseEntity.ok(documents);
//	}
//
//	@PostMapping("/{nomeFlusso}")
//	public ResponseEntity<BasicDBObject> insertDocument(@PathVariable String nomeFlusso,
//			@RequestBody HashMap<String, Object> params) {
//		log.debug("insert element for flusso {0} with params {1} ", nomeFlusso, params);
//		BasicDBObject document;
//		if ("farmaci".equalsIgnoreCase(nomeFlusso)) {
//			document = flussoFarmaciService.insertElement(params);
//		} else {
//			document = flussiService.insertElement(nomeFlusso, params);
//		}
//		return ResponseEntity.ok(document);
//	}

}
