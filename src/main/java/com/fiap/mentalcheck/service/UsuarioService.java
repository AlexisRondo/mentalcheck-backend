package com.fiap.mentalcheck.service;

import com.fiap.mentalcheck.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {
    
    Page<Usuario> findAll(Pageable pageable);
    
    Usuario findById(Long id);
    
    Usuario saveOrUpdate(Usuario usuario);
    
    void deleteById(Long id);
    
    Usuario findByEmail(String email);
}
