package com.fiap.mentalcheck.repository;

import com.fiap.mentalcheck.entity.Checkin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Long> {
    
    Page<Checkin> findByUsuarioId(Long idUsuario, Pageable pageable);
    
    @Query("SELECT c FROM Checkin c WHERE c.usuario.id = :idUsuario " +
           "AND c.dataCheckin = CURRENT_DATE")
    List<Checkin> findCheckinHojeByUsuario(@Param("idUsuario") Long idUsuario);
    
    @Query("SELECT c FROM Checkin c WHERE c.usuario.id = :idUsuario " +
           "AND c.dataCheckin BETWEEN :dataInicio AND :dataFim " +
           "ORDER BY c.dataCheckin DESC")
    List<Checkin> findByUsuarioAndPeriodo(
        @Param("idUsuario") Long idUsuario,
        @Param("dataInicio") LocalDate dataInicio,
        @Param("dataFim") LocalDate dataFim
    );
}
