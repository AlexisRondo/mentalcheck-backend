package com.fiap.mentalcheck.dto.dica;

public record DicaOutDto(
    Long id,
    String titulo,
    String descricao,
    String categoria,
    String condicaoAplicacao
) {}
