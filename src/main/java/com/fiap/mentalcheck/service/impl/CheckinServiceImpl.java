package com.fiap.mentalcheck.service.impl;

import com.fiap.mentalcheck.entity.Checkin;
import com.fiap.mentalcheck.exception.BusinessException;
import com.fiap.mentalcheck.exception.EntityNotFoundException;
import com.fiap.mentalcheck.repository.CheckinRepository;
import com.fiap.mentalcheck.repository.UsuarioRepository;
import com.fiap.mentalcheck.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CheckinServiceImpl implements CheckinService {
    
    private final CheckinRepository checkinRepository;
    private final UsuarioRepository usuarioRepository;
    
    @Autowired
    public CheckinServiceImpl(CheckinRepository checkinRepository, 
                             UsuarioRepository usuarioRepository) {
        this.checkinRepository = checkinRepository;
        this.usuarioRepository = usuarioRepository;
    }
    
    @Override
    public Page<Checkin> findAll(Pageable pageable) {
        return checkinRepository.findAll(pageable);
    }
    
    @Override
    public Page<Checkin> findByUsuario(Long idUsuario, Pageable pageable) {
        return checkinRepository.findByUsuarioId(idUsuario, pageable);
    }
    
    @Override
    public Checkin findById(Long id) {
        return checkinRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Check-in não encontrado com ID: " + id));
    }
    
    @Override
    @Transactional
    public Checkin saveOrUpdate(Checkin checkin) {
        if (checkin.getId() == null) {
            if (usuarioJaFezCheckinHoje(checkin.getUsuario().getId())) {
                throw new BusinessException("Usuário já fez check-in hoje");
            }
        }
        
        if (checkin.getUsuario() != null && checkin.getUsuario().getId() != null) {
            var usuario = usuarioRepository.findById(checkin.getUsuario().getId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
            checkin.setUsuario(usuario);
        }
        
        return checkinRepository.save(checkin);
    }
    
    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!checkinRepository.existsById(id)) {
            throw new EntityNotFoundException("Check-in não encontrado com ID: " + id);
        }
        checkinRepository.deleteById(id);
    }
    
    @Override
    public boolean usuarioJaFezCheckinHoje(Long idUsuario) {
        List<Checkin> checkinsHoje = checkinRepository.findCheckinHojeByUsuario(idUsuario);
        return !checkinsHoje.isEmpty();
    }
    
    @Override
    public List<Checkin> findByUsuarioAndPeriodo(Long idUsuario, LocalDate dataInicio, LocalDate dataFim) {
        return checkinRepository.findByUsuarioAndPeriodo(idUsuario, dataInicio, dataFim);
    }
}
