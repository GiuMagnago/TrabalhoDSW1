package br.ufscar.dc.dsw.domain;
import java.util.Date;

public class Profissional extends Usuario {
    private long idProfissional;
    private String CPF;
    private String nome;
    private String telefone;
    private String sexo;
    private Date dataNasc;

    public Profissional(long idUsuario, long idProfissional) {
        super(idUsuario);
        this.idProfissional = idProfissional;
    }

    public Profissional(long idUsuario, String email, String senha, String CPF, String nome, String telefone, String sexo, Date dataNasc) {
        super(idUsuario, email, senha);
        this.CPF = CPF;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
    }
    

    public long getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(long idProfissional) {
        this.idProfissional = idProfissional;
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
