package com.fiap.mentalcheck.repository;

import com.fiap.mentalcheck.entity.Dica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DicaRepository extends JpaRepository<Dica, Long> {
    
    Page<Dica> findByCategoria(String categoria, Pageable pageable);
}
