package com.fiap.mentalcheck.dto.usuario;

import com.fiap.mentalcheck.entity.enums.ModalidadeTrabalho;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioInDto {
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nome;
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
    private String email;
    
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String senha;
    
    @Size(max = 50, message = "Cargo deve ter no máximo 50 caracteres")
    private String cargo;
    
    private ModalidadeTrabalho modalidadeTrabalho;
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getCargo() {
        return cargo;
    }
    
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public ModalidadeTrabalho getModalidadeTrabalho() {
        return modalidadeTrabalho;
    }
    
    public void setModalidadeTrabalho(ModalidadeTrabalho modalidadeTrabalho) {
        this.modalidadeTrabalho = modalidadeTrabalho;
    }
}
