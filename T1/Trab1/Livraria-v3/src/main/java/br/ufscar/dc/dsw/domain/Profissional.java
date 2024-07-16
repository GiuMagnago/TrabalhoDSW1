package br.ufscar.dc.dsw.domain;
import java.util.Date;

public class Profissional {

    private String email;
    private String senha;
    private String CPF;
    private String nome;
    private String sexo;
    private Date dataNasc;
    private String telefone;

    public Profissional(String email) {
        this.email = email;
    }

    public Profissional(String email, String senha, String CPF, String nome, 
            String sexo, Date dataNasc, String telefone) {
        this(senha, CPF, nome, sexo, dataNasc);
        this.email = email;
        this.telefone = telefone;
    }

    public Profissional(String senha, String CPF, String nome, String sexo,
            Date dataNasc) {
        this.senha = senha;
        this.CPF = CPF;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
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

    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        this.nome = nome;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
