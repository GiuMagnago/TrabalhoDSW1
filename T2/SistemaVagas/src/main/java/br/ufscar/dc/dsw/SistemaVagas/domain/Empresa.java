package br.ufscar.dc.dsw.SistemaVagas.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Empresa")
public class Empresa extends Usuario {
    @Column(nullable = false, unique = true, length = 14)
    private String cpnj;
    
    @Column(nullable = false, length = 256)
    private String nome;

    @Column(nullable = false, length = 256)
    private String descricao;

    @Column(nullable = false, length = 40)
    private String cidade;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.REMOVE)
    private List<Vaga> vagas;

    public String getCnpj() {
        return cpnj;
    }

    public void setCnpj(String cnpj) {
        this.cpnj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
}
