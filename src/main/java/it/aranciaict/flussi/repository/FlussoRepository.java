package it.aranciaict.flussi.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.aranciaict.flussi.model.Flusso;

public interface FlussoRepository extends MongoRepository<Flusso, String>{

	Optional<Flusso> findOneByNameEquals(String name);
}
