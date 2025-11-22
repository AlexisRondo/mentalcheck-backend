package com.fiap.mentalcheck.mapper;

import com.fiap.mentalcheck.dto.checkin.CheckinInDto;
import com.fiap.mentalcheck.dto.checkin.CheckinOutDto;
import com.fiap.mentalcheck.entity.Checkin;

public class CheckinMapper {
    
    public static Checkin toEntity(CheckinInDto dto) {
        Checkin checkin = new Checkin();
        checkin.setNivelEstresse(dto.getNivelEstresse());
        checkin.setNivelMotivacao(dto.getNivelMotivacao());
        checkin.setNivelCansaco(dto.getNivelCansaco());
        checkin.setNivelSatisfacao(dto.getNivelSatisfacao());
        checkin.setQualidadeSono(dto.getQualidadeSono());
        checkin.setLocalTrabalho(dto.getLocalTrabalho());
        checkin.setObservacao(dto.getObservacao());
        return checkin;
    }
    
    public static CheckinOutDto toOutDto(Checkin entity) {
        return new CheckinOutDto(
            entity.getId(),
            entity.getUsuario().getId(),
            entity.getUsuario().getNome(),
            entity.getDataCheckin(),
            entity.getNivelEstresse(),
            entity.getNivelMotivacao(),
            entity.getNivelCansaco(),
            entity.getNivelSatisfacao(),
            entity.getQualidadeSono(),
            entity.getLocalTrabalho(),
            entity.getObservacao()
        );
    }
}
