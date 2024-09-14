package br.ufscar.dc.dsw.SistemaVagas.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.ufscar.dc.dsw.SistemaVagas.validation.NotNegative;
import br.ufscar.dc.dsw.SistemaVagas.validation.NotPast;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Vaga")
public class Vaga {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotBlank(message = "{NotBlank.empresa.cnpj}")
    @Size(min = 18, max = 18)
    @Column(nullable = false, length = 18)
    private String cnpj_empresa;

    @NotNegative(message = "{NotNegative}")
    @NotNull(message = "{NotNull.vaga.remuneracao}")
    @Column(nullable = false)
    private double remuneracao;

    @NotBlank(message = "{NotBlank.vaga.descricao}")
    @Size(max = 256)
    @Column(nullable = false, length = 256)
    private String descricao;

    @NotPast(message = "{NotPast}")
    @NotNull(message = "{NotNull.vaga.dataLimite}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date dataLimite;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @JsonIgnore
    @OneToMany(mappedBy = "vaga", cascade = CascadeType.REMOVE)
    private List<Candidatura> candidaturas;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCnpj_empresa() {
        return cnpj_empresa;
    }

    public void setCnpj_empresa(String cnpj_empresa) {
        this.cnpj_empresa = cnpj_empresa;
    }

    public double getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(double remuneracao) {
        this.remuneracao = remuneracao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Date d) {
        this.dataLimite = d;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Candidatura> getCandidaturas() {
        return candidaturas;
    }

    public void setCandidaturas(List<Candidatura> candidaturas) {
        this.candidaturas = candidaturas;
    }
}
