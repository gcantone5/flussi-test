package it.aranciaict.flussi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import it.aranciaict.flussi.model.TesseraSanit;

public interface TesseraSanitRepository extends MongoRepository<TesseraSanit, String> {

	TesseraSanit findOneByCodiceFiscaleEquals(String codiceFiscale);

	@Query("{ 'qa' : { $exists: true, $ne: [] } }")
	List<TesseraSanit> findAllWithQa();
	
	List<TesseraSanit> findAllByFlussoIdEquals(String flussoId);
	
}
