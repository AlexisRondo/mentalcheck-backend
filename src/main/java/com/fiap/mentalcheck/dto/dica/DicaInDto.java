package com.fiap.mentalcheck.dto.dica;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DicaInDto {
    
    @NotBlank(message = "Título é obrigatório")
    @Size(max = 50, message = "Título deve ter no máximo 50 caracteres")
    private String titulo;
    
    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    private String descricao;
    
    @NotBlank(message = "Categoria é obrigatória")
    @Size(max = 50, message = "Categoria deve ter no máximo 50 caracteres")
    private String categoria;
    
    @Size(max = 100, message = "Condição de aplicação deve ter no máximo 100 caracteres")
    private String condicaoAplicacao;
    
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
}
