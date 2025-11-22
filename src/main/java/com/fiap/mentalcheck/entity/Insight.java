package com.fiap.mentalcheck.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GS_INSIGHT")
public class Insight {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_insight")
    @SequenceGenerator(name = "seq_insight", sequenceName = "SEQ_INSIGHT", allocationSize = 1)
    @Column(name = "id_insight")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;
    
    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;
    
    @Column(name = "data_identificacao", nullable = false)
    private LocalDate dataIdentificacao;
    
    @OneToMany(mappedBy = "insight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InsightDica> insightDicas = new ArrayList<>();
    
    @PrePersist
    protected void onCreate() {
        if (dataIdentificacao == null) {
            dataIdentificacao = LocalDate.now();
        }
    }
    
    public void addDica(Dica dica) {
        InsightDica insightDica = new InsightDica();
        insightDica.setInsight(this);
        insightDica.setDica(dica);
        insightDicas.add(insightDica);
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
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public LocalDate getDataIdentificacao() {
        return dataIdentificacao;
    }
    
    public void setDataIdentificacao(LocalDate dataIdentificacao) {
        this.dataIdentificacao = dataIdentificacao;
    }
    
    public List<InsightDica> getInsightDicas() {
        return insightDicas;
    }
    
    public void setInsightDicas(List<InsightDica> insightDicas) {
        this.insightDicas = insightDicas;
    }
}
