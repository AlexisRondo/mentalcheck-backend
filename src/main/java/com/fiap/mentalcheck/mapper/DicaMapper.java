package com.fiap.mentalcheck.mapper;

import com.fiap.mentalcheck.dto.dica.DicaInDto;
import com.fiap.mentalcheck.dto.dica.DicaOutDto;
import com.fiap.mentalcheck.entity.Dica;

public class DicaMapper {
    
    public static Dica toEntity(DicaInDto dto) {
        Dica dica = new Dica();
        dica.setTitulo(dto.getTitulo());
        dica.setDescricao(dto.getDescricao());
        dica.setCategoria(dto.getCategoria());
        dica.setCondicaoAplicacao(dto.getCondicaoAplicacao());
        return dica;
    }
    
    public static DicaOutDto toOutDto(Dica entity) {
        return new DicaOutDto(
            entity.getId(),
            entity.getTitulo(),
            entity.getDescricao(),
            entity.getCategoria(),
            entity.getCondicaoAplicacao()
        );
    }
}
