package br.ufscar.dc.dsw.domain;
import java.util.Date;

public class Profissional {
    private long id;
    private String email;
    private String senha;
    private String CPF;
    private String nome;
    private String telefone;
    private String sexo;
    private Date dataNasc;

    public Profissional(long id) {
        this.id = id;
    }

    public Profissional(String email, String senha, String CPF, String nome, String telefone, String sexo, Date dataNasc) {
        this.email = email;
        this.senha = senha;
        this.CPF = CPF;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
    }
    
    public Profissional(long id, String email, String senha, String CPF, String nome, String telefone, String sexo, Date dataNasc) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.CPF = CPF;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
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

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
}
