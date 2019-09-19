package it.aranciaict.flussi.service;

import java.util.List;

import it.aranciaict.flussi.model.Flusso;

public interface FlussoService {

	Flusso create(String name);

	Flusso save(Flusso flusso);

	List<Flusso> findAll();

}
