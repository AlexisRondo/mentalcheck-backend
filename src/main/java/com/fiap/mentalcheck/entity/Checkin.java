package com.fiap.mentalcheck.entity;

import com.fiap.mentalcheck.entity.enums.LocalTrabalho;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "GS_CHECKIN")
public class Checkin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_checkin")
    @SequenceGenerator(name = "seq_checkin", sequenceName = "SEQ_CHECKIN", allocationSize = 1)
    @Column(name = "id_checkin")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @Column(name = "data_checkin", nullable = false)
    private LocalDate dataCheckin;
    
    @Column(name = "nivel_estresse", nullable = false)
    private Integer nivelEstresse;
    
    @Column(name = "nivel_motivacao", nullable = false)
    private Integer nivelMotivacao;
    
    @Column(name = "nivel_cansaco", nullable = false)
    private Integer nivelCansaco;
    
    @Column(name = "nivel_satisfacao", nullable = false)
    private Integer nivelSatisfacao;
    
    @Column(name = "qualidade_sono", nullable = false)
    private Integer qualidadeSono;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "local_trabalho", length = 20)
    private LocalTrabalho localTrabalho;
    
    @Column(name = "observacao", length = 500)
    private String observacao;
    
    @PrePersist
    protected void onCreate() {
        if (dataCheckin == null) {
            dataCheckin = LocalDate.now();
        }
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public LocalDate getDataCheckin() {
        return dataCheckin;
    }
    
    public void setDataCheckin(LocalDate dataCheckin) {
        this.dataCheckin = dataCheckin;
    }
    
    public Integer getNivelEstresse() {
        return nivelEstresse;
    }
    
    public void setNivelEstresse(Integer nivelEstresse) {
        this.nivelEstresse = nivelEstresse;
    }
    
    public Integer getNivelMotivacao() {
        return nivelMotivacao;
    }
    
    public void setNivelMotivacao(Integer nivelMotivacao) {
        this.nivelMotivacao = nivelMotivacao;
    }
    
    public Integer getNivelCansaco() {
        return nivelCansaco;
    }
    
    public void setNivelCansaco(Integer nivelCansaco) {
        this.nivelCansaco = nivelCansaco;
    }
    
    public Integer getNivelSatisfacao() {
        return nivelSatisfacao;
    }
    
    public void setNivelSatisfacao(Integer nivelSatisfacao) {
        this.nivelSatisfacao = nivelSatisfacao;
    }
    
    public Integer getQualidadeSono() {
        return qualidadeSono;
    }
    
    public void setQualidadeSono(Integer qualidadeSono) {
        this.qualidadeSono = qualidadeSono;
    }
    
    public LocalTrabalho getLocalTrabalho() {
        return localTrabalho;
    }
    
    public void setLocalTrabalho(LocalTrabalho localTrabalho) {
        this.localTrabalho = localTrabalho;
    }
    
    public String getObservacao() {
        return observacao;
    }
    
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
