package com.fiap.mentalcheck.entity;

import com.fiap.mentalcheck.entity.enums.ModalidadeTrabalho;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "GS_USUARIO")
public class Usuario implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name = "seq_usuario", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @Column(name = "id_usuario")
    private Long id;
    
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(name = "senha", nullable = false, length = 200)
    private String senha;
    
    @Column(name = "cargo", length = 50)
    private String cargo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "modalidade_trabalho", length = 20)
    private ModalidadeTrabalho modalidadeTrabalho;
    
    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Checkin> checkins = new ArrayList<>();
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Insight> insights = new ArrayList<>();
    
    @PrePersist
    protected void onCreate() {
        if (dataCadastro == null) {
            dataCadastro = LocalDate.now();
        }
    }
    
    public void addCheckin(Checkin checkin) {
        checkins.add(checkin);
        checkin.setUsuario(this);
    }
    
    public void removeCheckin(Checkin checkin) {
        checkins.remove(checkin);
        checkin.setUsuario(null);
    }
    
    public void addInsight(Insight insight) {
        insights.add(insight);
        insight.setUsuario(this);
    }
    
    public void removeInsight(Insight insight) {
        insights.remove(insight);
        insight.setUsuario(null);
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    
    @Override
    public String getPassword() {
        return senha;
    }
    
    @Override
    public String getUsername() {
        return email;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public LocalDate getDataCadastro() {
        return dataCadastro;
    }
    
    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    public List<Checkin> getCheckins() {
        return checkins;
    }
    
    public void setCheckins(List<Checkin> checkins) {
        this.checkins = checkins;
    }
    
    public List<Insight> getInsights() {
        return insights;
    }
    
    public void setInsights(List<Insight> insights) {
        this.insights = insights;
    }
}
