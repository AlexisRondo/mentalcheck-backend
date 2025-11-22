package com.fiap.mentalcheck.controller;

import com.fiap.mentalcheck.dto.usuario.UsuarioInDto;
import com.fiap.mentalcheck.dto.usuario.UsuarioOutDto;
import com.fiap.mentalcheck.entity.Usuario;
import com.fiap.mentalcheck.mapper.UsuarioMapper;
import com.fiap.mentalcheck.service.ProcedureService;
import com.fiap.mentalcheck.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    private final ProcedureService procedureService;
    
    @Autowired
    public UsuarioController(UsuarioService usuarioService, ProcedureService procedureService) {
        this.usuarioService = usuarioService;
        this.procedureService = procedureService;
    }
    
    @GetMapping
    @SecurityRequirement(name = "bearer-auth")
    @Operation(summary = "Listar usuários com paginação")
    public ResponseEntity<Page<UsuarioOutDto>> findAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UsuarioOutDto> usuarios = usuarioService.findAll(pageable)
            .map(UsuarioMapper::toOutDto);
        return ResponseEntity.ok(usuarios);
    }
    
    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-auth")
    @Operation(summary = "Buscar usuário por ID")
    public ResponseEntity<UsuarioOutDto> findById(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok(UsuarioMapper.toOutDto(usuario));
    }
    
    @PostMapping
    @Operation(summary = "Criar novo usuário")
    public ResponseEntity<UsuarioOutDto> create(@Valid @RequestBody UsuarioInDto dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        Usuario saved = usuarioService.saveOrUpdate(usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(UsuarioMapper.toOutDto(saved));
    }
    
    @PostMapping("/procedure")
    @Operation(summary = "Criar usuário via stored procedure")
    public ResponseEntity<Long> createViaProcedure(@Valid @RequestBody UsuarioInDto dto) {
        String modalidade = dto.getModalidadeTrabalho() != null 
            ? dto.getModalidadeTrabalho().name() 
            : null;
        
        Long idUsuario = procedureService.inserirUsuarioProcedure(
            dto.getNome(),
            dto.getEmail(),
            dto.getSenha(),
            dto.getCargo(),
            modalidade
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(idUsuario);
    }
    
    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearer-auth")
    @Operation(summary = "Atualizar usuário")
    public ResponseEntity<UsuarioOutDto> update(
        @PathVariable Long id,
        @Valid @RequestBody UsuarioInDto dto
    ) {
        Usuario usuario = usuarioService.findById(id);
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setCargo(dto.getCargo());
        usuario.setModalidadeTrabalho(dto.getModalidadeTrabalho());
        
        Usuario updated = usuarioService.saveOrUpdate(usuario);
        return ResponseEntity.ok(UsuarioMapper.toOutDto(updated));
    }
    
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearer-auth")
    @Operation(summary = "Deletar usuário")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
