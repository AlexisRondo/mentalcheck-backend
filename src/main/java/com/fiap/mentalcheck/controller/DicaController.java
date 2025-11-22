package com.fiap.mentalcheck.controller;

import com.fiap.mentalcheck.dto.dica.DicaInDto;
import com.fiap.mentalcheck.dto.dica.DicaOutDto;
import com.fiap.mentalcheck.entity.Dica;
import com.fiap.mentalcheck.mapper.DicaMapper;
import com.fiap.mentalcheck.repository.DicaRepository;
import com.fiap.mentalcheck.service.ProcedureService;
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
@RequestMapping("/dicas")
@SecurityRequirement(name = "bearer-auth")
@Tag(name = "Dicas", description = "Gerenciamento de dicas de bem-estar")
public class DicaController {
    
    private final DicaRepository dicaRepository;
    private final ProcedureService procedureService;
    
    @Autowired
    public DicaController(DicaRepository dicaRepository, ProcedureService procedureService) {
        this.dicaRepository = dicaRepository;
        this.procedureService = procedureService;
    }
    
    @GetMapping
    @Operation(summary = "Listar todas as dicas com paginação")
    public ResponseEntity<Page<DicaOutDto>> findAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DicaOutDto> dicas = dicaRepository.findAll(pageable)
            .map(DicaMapper::toOutDto);
        return ResponseEntity.ok(dicas);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar dica por ID")
    public ResponseEntity<DicaOutDto> findById(@PathVariable Long id) {
        Dica dica = dicaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Dica não encontrada"));
        return ResponseEntity.ok(DicaMapper.toOutDto(dica));
    }
    
    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Buscar dicas por categoria")
    public ResponseEntity<Page<DicaOutDto>> findByCategoria(
        @PathVariable String categoria,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DicaOutDto> dicas = dicaRepository.findByCategoria(categoria, pageable)
            .map(DicaMapper::toOutDto);
        return ResponseEntity.ok(dicas);
    }
    
    @PostMapping
    @Operation(summary = "Criar nova dica")
    public ResponseEntity<DicaOutDto> create(@Valid @RequestBody DicaInDto dto) {
        Dica dica = DicaMapper.toEntity(dto);
        Dica saved = dicaRepository.save(dica);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(DicaMapper.toOutDto(saved));
    }
    
    @PostMapping("/procedure")
    @Operation(summary = "Criar dica via stored procedure")
    public ResponseEntity<Long> createViaProcedure(@Valid @RequestBody DicaInDto dto) {
        Long idDica = procedureService.inserirDicaProcedure(
            dto.getTitulo(),
            dto.getDescricao(),
            dto.getCategoria(),
            dto.getCondicaoAplicacao()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(idDica);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar dica")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dicaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
