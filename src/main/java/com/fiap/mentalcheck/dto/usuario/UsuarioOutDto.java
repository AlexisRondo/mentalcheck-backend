package com.fiap.mentalcheck.dto.usuario;

import com.fiap.mentalcheck.entity.enums.ModalidadeTrabalho;

import java.time.LocalDate;

public record UsuarioOutDto(
    Long id,
    String nome,
    String email,
    String cargo,
    ModalidadeTrabalho modalidadeTrabalho,
    LocalDate dataCadastro
) {}
