package it.aranciaict.flussi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.aranciaict.flussi.model.Flusso;
import it.aranciaict.flussi.repository.FlussoRepository;
import it.aranciaict.flussi.service.FlussoService;

@Service
public class FlussoServiceImpl implements FlussoService {

	@Autowired
	private FlussoRepository flussoRepository;

	@Override
	public Flusso create(String name) {
		Flusso flusso = new Flusso();
		flusso.setName(name);
		return this.save(flusso);
	}

	@Override
	public Flusso save(Flusso flusso) {
		return flussoRepository.save(flusso);
	}

	@Override
	public List<Flusso> findAll() {
		return flussoRepository.findAll();
	}

}
