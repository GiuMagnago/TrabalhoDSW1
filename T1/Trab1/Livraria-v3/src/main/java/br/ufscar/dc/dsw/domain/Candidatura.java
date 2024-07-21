package br.ufscar.dc.dsw.domain;

public class Candidatura {
    private long id_profissional;
    private long id_vaga;
    private String statusCandidatura;

    public Candidatura(long id_profissional, long id_vaga) {
        this.id_profissional = id_profissional;
        this.id_vaga = id_vaga;
        this.statusCandidatura = "ABERTO";
    }

    public Candidatura(long id_profissional, long id_vaga, String statusCandidatura) {
        this.id_profissional = id_profissional;
        this.id_vaga = id_vaga;
        this.statusCandidatura = statusCandidatura;
    }

    public void setIdProfissional(long id_profissional) {
        this.id_profissional = id_profissional;
    }

    public long getIdProfissional() {
        return id_profissional;
    }

    public void setIdVaga(long id_vaga) {
        this.id_vaga = id_vaga;
    }

    public long getIdVaga() {
        return id_vaga;
    }

    public void setStatusCandidatura(String statusCandidatura) {
        this.statusCandidatura = statusCandidatura;
    }

    public String getStatusCandidatura() {
        return statusCandidatura;
    }
}
