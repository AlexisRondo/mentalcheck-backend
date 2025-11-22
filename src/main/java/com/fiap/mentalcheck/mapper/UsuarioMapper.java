package com.fiap.mentalcheck.mapper;

import com.fiap.mentalcheck.dto.usuario.UsuarioInDto;
import com.fiap.mentalcheck.dto.usuario.UsuarioOutDto;
import com.fiap.mentalcheck.entity.Usuario;

public class UsuarioMapper {
    
    public static Usuario toEntity(UsuarioInDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setCargo(dto.getCargo());
        usuario.setModalidadeTrabalho(dto.getModalidadeTrabalho());
        return usuario;
    }
    
    public static UsuarioOutDto toOutDto(Usuario entity) {
        return new UsuarioOutDto(
            entity.getId(),
            entity.getNome(),
            entity.getEmail(),
            entity.getCargo(),
            entity.getModalidadeTrabalho(),
            entity.getDataCadastro()
        );
    }
}
