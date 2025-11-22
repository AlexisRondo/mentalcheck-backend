package com.fiap.mentalcheck.service;

import com.fiap.mentalcheck.entity.Checkin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface CheckinService {
    
    Page<Checkin> findAll(Pageable pageable);
    
    Page<Checkin> findByUsuario(Long idUsuario, Pageable pageable);
    
    Checkin findById(Long id);
    
    Checkin saveOrUpdate(Checkin checkin);
    
    void deleteById(Long id);
    
    boolean usuarioJaFezCheckinHoje(Long idUsuario);
    
    List<Checkin> findByUsuarioAndPeriodo(Long idUsuario, LocalDate dataInicio, LocalDate dataFim);
}
