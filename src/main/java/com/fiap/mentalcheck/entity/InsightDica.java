package com.fiap.mentalcheck.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "GS_INSIGHT_DICA")
public class InsightDica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_insight_dica")
    @SequenceGenerator(name = "seq_insight_dica", sequenceName = "SEQ_INSIGHT_DICA", allocationSize = 1)
    @Column(name = "id_insight_dica")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_insight", nullable = false)
    private Insight insight;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dica", nullable = false)
    private Dica dica;
    
    @Column(name = "data_recomendacao", nullable = false)
    private LocalDate dataRecomendacao;
    
    @Column(name = "status_visualizacao", length = 20)
    private String statusVisualizacao;
    
    @PrePersist
    protected void onCreate() {
        if (dataRecomendacao == null) {
            dataRecomendacao = LocalDate.now();
        }
        if (statusVisualizacao == null) {
            statusVisualizacao = "PENDENTE";
        }
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Insight getInsight() {
        return insight;
    }
    
    public void setInsight(Insight insight) {
        this.insight = insight;
    }
    
    public Dica getDica() {
        return dica;
    }
    
    public void setDica(Dica dica) {
        this.dica = dica;
    }
    
    public LocalDate getDataRecomendacao() {
        return dataRecomendacao;
    }
    
    public void setDataRecomendacao(LocalDate dataRecomendacao) {
        this.dataRecomendacao = dataRecomendacao;
    }
    
    public String getStatusVisualizacao() {
        return statusVisualizacao;
    }
    
    public void setStatusVisualizacao(String statusVisualizacao) {
        this.statusVisualizacao = statusVisualizacao;
    }
}
