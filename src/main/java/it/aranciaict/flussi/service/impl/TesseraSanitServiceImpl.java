package it.aranciaict.flussi.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import it.aranciaict.flussi.model.TesseraSanit;
import it.aranciaict.flussi.model.dto.TesseraSanitDTO;
import it.aranciaict.flussi.repository.TesseraSanitRepository;
import it.aranciaict.flussi.service.TesseraSanitService;
import it.aranciaict.flussi.service.mapper.TesseraSanitMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TesseraSanitServiceImpl implements TesseraSanitService {

	@Autowired
	private TesseraSanitRepository tesseraSanitRepository;

	@Autowired
	private TesseraSanitMapper tesseraSanitMapper;

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public List<TesseraSanitDTO> getAllTesseraSanit() {
		log.debug("TesseraSanitService - getAllTesseraSanit");
		return tesseraSanitRepository.findAll().stream().map(tesseraSanitMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public TesseraSanitDTO getOneByCodiceFiscaleEquals(String codiceFiscale) {
		log.debug("TesseraSanitService - getOneByCodiceFiscaleEquals");
		return tesseraSanitMapper.toDto(tesseraSanitRepository.findOneByCodiceFiscaleEquals(codiceFiscale));
	}

	@Override
	public List<TesseraSanitDTO> validateTessereSanit() {
		log.debug("TesseraSanitService - validateTessereSanit");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		List<TesseraSanit> allTesseraSanit = tesseraSanitRepository.findAll();
		for (TesseraSanit tesseraSanit : allTesseraSanit) {
			tesseraSanit.resetViolations();
			Set<ConstraintViolation<TesseraSanit>> validationErrors = validator.validate(tesseraSanit);
			if (!CollectionUtils.isEmpty(validationErrors)) {
				for (ConstraintViolation<TesseraSanit> violation : validationErrors) {
					tesseraSanit.addViolation(violation.getMessage());
				}
			}
			log.debug("Update tesseraSanit {0}", tesseraSanit.getId());
			Query query = new Query(Criteria.where("_id").is(tesseraSanit.getId()));
			Update update = new Update().set("qa", tesseraSanit.getQa());
			mongoOperations.findAndModify(query, update, new FindAndModifyOptions().returnNew(true),
					TesseraSanit.class);
		}
		return allTesseraSanit.stream().map(tesseraSanitMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public List<TesseraSanitDTO> getAllTesseraSanitKO() {
		log.debug("TesseraSanitService - getAllTesseraSanitKO");
		return tesseraSanitRepository.findAllWithQa().stream().map(tesseraSanitMapper::toDto)
				.collect(Collectors.toList());
	}
	

	@Override
	public List<TesseraSanitDTO> getAllByFlussoId(String flussoId) {
		log.debug("TesseraSanitService - getAllByFlusso");
		List<TesseraSanit> findAllByFlussoIdEquals = tesseraSanitRepository.findAllByFlussoIdEquals(new ObjectId(flussoId));
		return findAllByFlussoIdEquals.stream().map(tesseraSanitMapper::toDto)
				.collect(Collectors.toList());
	}
	
}
