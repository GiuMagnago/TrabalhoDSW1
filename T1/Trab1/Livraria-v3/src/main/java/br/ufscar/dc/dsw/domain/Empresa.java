package br.ufscar.dc.dsw.domain;

public class Empresa extends Usuario {
    private long idEmpresa;
    private String CNPJ;
    private String nome;
    private String descricao;
    private String cidade;

    public Empresa(long idUsuario, long idEmpresa) {
        super(idUsuario);
        this.idEmpresa = idEmpresa;
    }

    public Empresa(String email, String senha, String CNPJ, String nome, String descricao, String cidade) {
        super(email, senha);
        this.CNPJ = CNPJ;
        this.nome = nome;
        this.descricao = descricao;
        this.cidade = cidade;
    }

    public Empresa(long idUsuario, String email, String senha, String CNPJ, String nome, String descricao, String cidade) {
        super(idUsuario, email, senha);
        this.CNPJ = CNPJ;
        this.nome = nome;
        this.descricao = descricao;
        this.cidade = cidade;
    }

    public long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
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