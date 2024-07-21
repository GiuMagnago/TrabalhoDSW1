package br.ufscar.dc.dsw.domain;
import java.util.Date;

public class Vagas extends Empresa {
    private long id_vaga;
    private Date dataLimite;
    private String descricao;

    public Vagas(long id_vaga, long id_empresa){
        super(null, id_empresa);
        this.id_vaga = id_vaga;
    }

    public Vagas(Date dataLimite, String descricao){
        this.dataLimite = dataLimite;
        this.descricao = descricao;
    }

    public Vagas(long id_empresa, Date dataLimite, String descricao){
        super(id_empresa);
        this.dataLimite = dataLimite;
        this.descricao = descricao;
    }

    public void setIdVaga(long id_vaga){
        this.id_vaga = id_vaga;
    }

    public long getIdVaga(){
        return this.id_vaga;
    }

    public void setDataLimite(Date dataLimite){
        this.dataLimite = dataLimite;
    }
    public Date getDataLimite(){
        return this.dataLimite;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }
}