package br.ufscar.dc.dsw.SistemaVagas.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.ufscar.dc.dsw.SistemaVagas.validation.UniqueCNPJ;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Empresa")
public class Empresa extends Usuario {
    @UniqueCNPJ(message = "{UniqueCNPJ}")
    @NotBlank(message = "{NotBlank.empresa.cnpj}")
    @Size(min = 18, max = 18)
    @Column(nullable = false, unique = true, length = 18)
    private String cnpj;

    @NotBlank(message = "{NotBlank.empresa.descricao}")
    @Column(nullable = false, length = 256)
    private String descricao;

    @NotBlank(message = "{NotBlank.empresa.cidade}")
    @Column(nullable = false, length = 40)
    private String cidade;

    @JsonIgnore
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.REMOVE)
    private List<Vaga> vagas;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public List<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<Vaga> vagas) {
        this.vagas = vagas;
    }
}
