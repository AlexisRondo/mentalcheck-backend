package com.fiap.mentalcheck.dto.checkin;

import com.fiap.mentalcheck.entity.enums.LocalTrabalho;

import java.time.LocalDate;

public record CheckinOutDto(
    Long id,
    Long idUsuario,
    String nomeUsuario,
    LocalDate dataCheckin,
    Integer nivelEstresse,
    Integer nivelMotivacao,
    Integer nivelCansaco,
    Integer nivelSatisfacao,
    Integer qualidadeSono,
    LocalTrabalho localTrabalho,
    String observacao
) {}
