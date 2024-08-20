package br.ufscar.dc.dsw.SistemaVagas.domain;

import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Profissional")
public class Profissional extends Usuario {
    @NotBlank(message = "{NotBlank.profissional.cpf}")
    @Size(min = 11, max = 11)
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @NotBlank(message = "{NotBlank.profissional.telefone}")
    @Column(nullable = false, length = 15)
    private String telefone;

    @NotBlank(message = "{NotBlank.profissional.sexo}")
    @Column(nullable = false, length = 15)
    private String sexo;

    @NotNull(message = "{NotNull.profissional.dataNasc}")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false, length = 15)
    private Date dataNasc;

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.REMOVE)
    private List<Candidatura> Candidaturas;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public List<Candidatura> getCandidaturas() {
        return Candidaturas;
    }

    public void setCandidaturas(List<Candidatura> candidaturas) {
        Candidaturas = candidaturas;
    }
}
