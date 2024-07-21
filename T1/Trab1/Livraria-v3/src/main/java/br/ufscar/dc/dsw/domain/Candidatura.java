package br.ufscar.dc.dsw.domain;

class Candidatura extends Empresa {
    private Profissional profissional;
    private String statusCandidatura;
    private long id_candidatura;

    public Candidatura(long id_empresa, Profissional profissional, String statusCandidatura) {
        super(null, id_empresa);
        this.profissional = profissional;
        this.statusCandidatura = statusCandidatura;
    }

    public Candidatura(long id_empresa, String statusCandidatura) {
        super(null, id_empresa);
        this.statusCandidatura = statusCandidatura;
    }

    public String getStatusCandidatura() {
        return this.statusCandidatura;
    }

    public void setStatusCandidatura(String statusCandidatura) {
        this.statusCandidatura = statusCandidatura;
    }

    public long getId_candidatura(){
        return this.getId_candidatura;
    }

    public void setIdCandidatura(long id_candidatura){
        this.id_candidatura = id_candidatura;
    }
}
