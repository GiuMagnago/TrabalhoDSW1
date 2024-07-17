package br.ufscar.dc.dsw.domain;

public class Empresa {
    private long id;
    private String email;
    private String senha;
    private String CNPJ;
    private String nome;
    private String descricao;
    private String cidade;

    public Empresa() {}

    public Empresa(String email, String senha, String CNPJ, String nome, String descricao, String cidade) {
        this.email = email;
        this.senha = senha;
        this.CNPJ = CNPJ;
        this.nome = nome;
        this.descricao = descricao;
        this.cidade = cidade;
    }

    public Empresa(long id, String email, String senha, String CNPJ, String nome, String descricao, String cidade) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.CNPJ = CNPJ;
        this.nome = nome;
        this.descricao = descricao;
        this.cidade = cidade;
    }

    public long getId() {
        return id;
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

    public void setsenha(String senha) {
        this.senha = senha;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
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