package br.ufscar.dc.dsw.SistemaVagas.domain;

import java.sql.Date;
import java.util.List;

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

@SuppressWarnings("serial")
@Entity
@Table(name = "Vaga")
public class Vaga {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, length = 14)
    private String cnpj_empresa;

    @Column(nullable = false)
    private double remuneracao;

    @Column(nullable = false, length = 256)
    private String descricao;

    @Column(nullable = false)
    private Date dataLimite;

    @Column(nullable = true, length = 40)
    private String cidade;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

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

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

    public String getCidade(){
        return cidade;
    }

    public void setCidade(String cidade){
        this.cidade = cidade;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Candidatura> getCandidatura() {
        return candidaturas;
    }

    public void setCandidatura(List<Candidatura> candidaturas) {
        this.candidaturas = candidaturas;
    }
}
