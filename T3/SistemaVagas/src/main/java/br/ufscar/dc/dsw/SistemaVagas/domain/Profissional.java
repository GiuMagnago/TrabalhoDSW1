package br.ufscar.dc.dsw.SistemaVagas.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.ufscar.dc.dsw.SistemaVagas.validation.NotYoungerThan16;
import br.ufscar.dc.dsw.SistemaVagas.validation.UniqueCPF;
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
    @UniqueCPF(message = "{UniqueCPF}")
    @NotBlank(message = "{NotBlank.profissional.cpf}")
    @Size(min = 14, max = 14)
    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @NotBlank(message = "{NotBlank.profissional.telefone}")
    @Column(nullable = false, length = 14)
    private String telefone;

    @NotBlank(message = "{NotBlank.profissional.sexo}")
    @Column(nullable = false, length = 15)
    private String sexo;

    @NotYoungerThan16(message = "{NotYoungerThan16}")
    @NotNull(message = "{NotNull.profissional.dataNasc}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false, length = 15)
    private Date dataNasc;

    @JsonIgnore
    @OneToMany(mappedBy = "profissional", cascade = CascadeType.REMOVE)
    private List<Candidatura> candidaturas;

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
        return candidaturas;
    }

    public void setCandidaturas(List<Candidatura> candidaturas) {
        this.candidaturas = candidaturas;
    }
}
