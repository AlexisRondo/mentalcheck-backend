package com.fiap.mentalcheck.controller;

import com.fiap.mentalcheck.dto.checkin.CheckinInDto;
import com.fiap.mentalcheck.dto.checkin.CheckinOutDto;
import com.fiap.mentalcheck.dto.checkin.CheckinProcedureInDto;
import com.fiap.mentalcheck.entity.Checkin;
import com.fiap.mentalcheck.mapper.CheckinMapper;
import com.fiap.mentalcheck.service.CheckinService;
import com.fiap.mentalcheck.service.ProcedureService;
import com.fiap.mentalcheck.service.UsuarioService;
import com.fiap.mentalcheck.entity.Usuario;
import org.springframework.security.core.Authentication;
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

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/checkins")
@SecurityRequirement(name = "bearer-auth")
@Tag(name = "Check-ins", description = "Gerenciamento de check-ins diários")
public class CheckinController {
    
    private final CheckinService checkinService;
    private final UsuarioService usuarioService;
    private final ProcedureService procedureService;
    
    @Autowired
    public CheckinController(CheckinService checkinService, 
                            UsuarioService usuarioService,
                            ProcedureService procedureService) {
        this.checkinService = checkinService;
        this.usuarioService = usuarioService;
        this.procedureService = procedureService;
    }
    
    @GetMapping
    @Operation(summary = "Listar todos os check-ins com paginação")
    public ResponseEntity<Page<CheckinOutDto>> findAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CheckinOutDto> checkins = checkinService.findAll(pageable)
            .map(CheckinMapper::toOutDto);
        return ResponseEntity.ok(checkins);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar check-in por ID")
    public ResponseEntity<CheckinOutDto> findById(@PathVariable Long id) {
        Checkin checkin = checkinService.findById(id);
        return ResponseEntity.ok(CheckinMapper.toOutDto(checkin));
    }
    
    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Listar check-ins de um usuário")
    public ResponseEntity<Page<CheckinOutDto>> findByUsuario(
        @PathVariable Long idUsuario,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CheckinOutDto> checkins = checkinService.findByUsuario(idUsuario, pageable)
            .map(CheckinMapper::toOutDto);
        return ResponseEntity.ok(checkins);
    }
    
    @GetMapping("/usuario/{idUsuario}/periodo")
    @Operation(summary = "Buscar check-ins por período")
    public ResponseEntity<List<CheckinOutDto>> findByPeriodo(
        @PathVariable Long idUsuario,
        @RequestParam String dataInicio,
        @RequestParam String dataFim
    ) {
        LocalDate inicio = LocalDate.parse(dataInicio);
        LocalDate fim = LocalDate.parse(dataFim);
        
        List<CheckinOutDto> checkins = checkinService
            .findByUsuarioAndPeriodo(idUsuario, inicio, fim)
            .stream()
            .map(CheckinMapper::toOutDto)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(checkins);
    }
    
    @PostMapping
    @Operation(summary = "Criar novo check-in")
    public ResponseEntity<CheckinOutDto> create(@Valid @RequestBody CheckinInDto dto) {
        Checkin checkin = CheckinMapper.toEntity(dto);
        var usuario = usuarioService.findById(dto.getIdUsuario());
        checkin.setUsuario(usuario);
        
        Checkin saved = checkinService.saveOrUpdate(checkin);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(CheckinMapper.toOutDto(saved));
    }

    @PostMapping("/procedure")
    @Operation(summary = "Criar check-in via stored procedure")
    public ResponseEntity<Long> createViaProcedure(
            @Valid @RequestBody CheckinProcedureInDto dto,
            Authentication authentication) {

        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();

        if (usuarioAutenticado == null) {
            throw new RuntimeException("Usuário não autenticado");
        }

        String localTrabalho = dto.getLocalTrabalho() != null
                ? dto.getLocalTrabalho().name()
                : null;

        Long idCheckin = procedureService.inserirCheckinProcedure(
                usuarioAutenticado.getId(),
                dto.getNivelEstresse(),
                dto.getNivelMotivacao(),
                dto.getNivelCansaco(),
                dto.getNivelSatisfacao(),
                dto.getQualidadeSono(),
                localTrabalho,
                dto.getObservacao()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(idCheckin);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar check-in")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        checkinService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
