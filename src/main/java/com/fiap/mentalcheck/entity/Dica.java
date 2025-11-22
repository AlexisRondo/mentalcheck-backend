package com.fiap.mentalcheck.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GS_DICA")
public class Dica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_dica")
    @SequenceGenerator(name = "seq_dica", sequenceName = "SEQ_DICA", allocationSize = 1)
    @Column(name = "id_dica")
    private Long id;
    
    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;
    
    @Column(name = "descricao", nullable = false, length = 1000)
    private String descricao;
    
    @Column(name = "categoria", nullable = false, length = 50)
    private String categoria;
    
    @Column(name = "condicao_aplicacao", length = 100)
    private String condicaoAplicacao;
    
    @OneToMany(mappedBy = "dica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InsightDica> insightDicas = new ArrayList<>();
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public String getCondicaoAplicacao() {
        return condicaoAplicacao;
    }
    
    public void setCondicaoAplicacao(String condicaoAplicacao) {
        this.condicaoAplicacao = condicaoAplicacao;
    }
    
    public List<InsightDica> getInsightDicas() {
        return insightDicas;
    }
    
    public void setInsightDicas(List<InsightDica> insightDicas) {
        this.insightDicas = insightDicas;
    }
}
