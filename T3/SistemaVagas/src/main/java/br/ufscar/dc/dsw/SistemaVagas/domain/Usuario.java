package br.ufscar.dc.dsw.SistemaVagas.domain;

import br.ufscar.dc.dsw.SistemaVagas.validation.UniqueEmail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotBlank(message = "{NotBlank.usuario.nome}")
    @Column(nullable = false, length = 256)
    private String nome;

    @UniqueEmail(message = "{UniqueEmail}")
    @NotBlank(message = "{NotBlank.usuario.email}")
    @Size(max = 50)
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @NotBlank(message = "{NotBlank.usuario.senha}")
    @Column(nullable = false)
    private String senha;

    @NotBlank(message = "{NotBlank.usuario.papel}")
    @Column(nullable = false)
    private String papel;

    @Column(nullable = false)
    private boolean enable;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getPapel() {
        return papel;
    }
    
    public void setPapel(String papel) {
        this.papel = papel;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}