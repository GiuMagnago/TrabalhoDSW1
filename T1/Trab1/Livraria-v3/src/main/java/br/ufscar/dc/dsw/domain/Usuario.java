package br.ufscar.dc.dsw.domain;

public class Usuario {
    private long idUsuario;
    private String email;
    private String senha;

    public Usuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(long idUsuario, String email, String senha) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.senha = senha;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdUsuario() {
        return idUsuario;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getSenha() {
        return senha;
    }
}
