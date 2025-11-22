package com.fiap.mentalcheck.dto.checkin;

import com.fiap.mentalcheck.entity.enums.LocalTrabalho;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CheckinProcedureInDto {

    @NotNull(message = "Nível de estresse é obrigatório")
    @Min(value = 1, message = "Nível de estresse deve estar entre 1 e 10")
    @Max(value = 10, message = "Nível de estresse deve estar entre 1 e 10")
    private Integer nivelEstresse;

    @NotNull(message = "Nível de motivação é obrigatório")
    @Min(value = 1, message = "Nível de motivação deve estar entre 1 e 10")
    @Max(value = 10, message = "Nível de motivação deve estar entre 1 e 10")
    private Integer nivelMotivacao;

    @NotNull(message = "Nível de cansaço é obrigatório")
    @Min(value = 1, message = "Nível de cansaço deve estar entre 1 e 10")
    @Max(value = 10, message = "Nível de cansaço deve estar entre 1 e 10")
    private Integer nivelCansaco;

    @NotNull(message = "Nível de satisfação é obrigatório")
    @Min(value = 1, message = "Nível de satisfação deve estar entre 1 e 10")
    @Max(value = 10, message = "Nível de satisfação deve estar entre 1 e 10")
    private Integer nivelSatisfacao;

    @NotNull(message = "Qualidade do sono é obrigatória")
    @Min(value = 1, message = "Qualidade do sono deve estar entre 1 e 10")
    @Max(value = 10, message = "Qualidade do sono deve estar entre 1 e 10")
    private Integer qualidadeSono;

    private LocalTrabalho localTrabalho;

    @Size(max = 500, message = "Observação deve ter no máximo 500 caracteres")
    private String observacao;

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