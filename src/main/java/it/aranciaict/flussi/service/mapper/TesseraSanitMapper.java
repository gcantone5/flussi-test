package it.aranciaict.flussi.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import it.aranciaict.flussi.model.TesseraSanit;
import it.aranciaict.flussi.model.dto.TesseraSanitDTO;

@Mapper(componentModel = "spring")
public abstract class TesseraSanitMapper {
	
	public abstract TesseraSanitDTO toDto(TesseraSanit entity);
	
	public abstract List<TesseraSanitDTO> toDto(List<TesseraSanit> entity);

}
