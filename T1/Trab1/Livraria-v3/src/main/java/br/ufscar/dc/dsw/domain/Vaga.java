package br.ufscar.dc.dsw.domain;
import java.util.Date;

public class Vaga {
    private long id_vaga;
    private long id_empresa;
    private String cpnj_empresa;
    private String descricao;
    private double remuneracao;
    private Date dataLimite;
    

    public Vaga(long id_vaga){
        this.id_vaga = id_vaga;
    }

    public Vaga(long id_empresa, String cnpj_empresa, String descricao, double remuneracao, Date dataLimite) {
        this.id_empresa = id_empresa;
        this.cpnj_empresa = cnpj_empresa;
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.dataLimite = dataLimite;
    }

    public Vaga(long id_vaga, long id_empresa, String cnpj_empresa, String descricao, double remuneracao, Date dataLimite) {
        this.id_vaga = id_vaga;
        this.id_empresa = id_empresa;
        this.cpnj_empresa = cnpj_empresa;
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.dataLimite = dataLimite;
    }

    public void setIdVaga(long id_vaga) {
        this.id_vaga = id_vaga;
    }

    public long getIdVaga(){
        return this.id_vaga;
    }

    public void setIdEmpresa(long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public long getIdEmpresa(){
        return this.id_empresa;
    }

    public void setCnpjEmpresa(String cnpj_empresa) {
        this.cpnj_empresa = cnpj_empresa;
    }

    public String getCnpjEmpresa() {
        return cpnj_empresa;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setRemuneracao(double remuneracao) {
        this.remuneracao = remuneracao;
    }

    public double getRemuneracao() {
        return remuneracao;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }
    
    public Date getDataLimite(){
        return this.dataLimite;
    }

    
}