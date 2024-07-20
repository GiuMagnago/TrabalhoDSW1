package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;

public class UsuarioDAO extends GenericDAO {
    public boolean checkExistence(Connection conn, String email, String senha) {
        String sql = "SELECT * FROM Usuario WHERE email = ? or cpf = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, senha);
            
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return true;
    }

    public long inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario(email, senha) VALUES (?, ?)";
        long userId = 0;

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, usuario.getEmail());
            statement.setString(2, usuario.getSenha());
            if (checkExistence(conn, sql, sql)) {
                statement.executeQuery();
                ResultSet gK = statement.getGeneratedKeys();
                if (gK.next()) {
                    userId = gK.getLong(1);
                    /*
                     * método de inserção geral (insere todos os tipos de usuário), 
                     * então é preciso fazer o downcast e inseri-lo na tabela própria
                     */ 
                    if (usuario instanceof Empresa) {
                        EmpresaDAO dao = new EmpresaDAO();
                        dao.insert(userId, (Empresa) usuario);
                    } else if (usuario instanceof Profissional) {
                        ProfissionalDAO dao = new ProfissionalDAO();
                        dao.insert(userId, (Profissional) usuario);
                    }
                }
            }
            statement.close();
            conn.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return userId;
    }

    public boolean isEmpresa(Connection conn, long id) {
        String sql = "SELECT * FROM Empresa WHERE id_usuario = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    public Usuario getUsuario(String email, String senha) {
        String sql = "SELECT * FROM Usuario WHERE email = ? AND senha = ?";
        Usuario usuario = null;

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            statement.setString(2, senha);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                long userId = rs.getLong("id_usuario");

                if (isEmpresa(conn, userId)) {
                    EmpresaDAO dao = new EmpresaDAO();
                    usuario = dao.get(userId, email, senha);
                } else {
                    ProfissionalDAO dao = new ProfissionalDAO();
                    usuario = dao.get(userId, email, senha);
                }
            }
            statement.close();
            conn.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return usuario;
    }

    public void updateUsuario(Usuario usuario) {
        String sql = "UPDATE Usuario SET email = ? AND senha = ? WHERE id_usuario = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getEmail());
            statement.setString(2, usuario.getSenha());
            statement.setLong(3, usuario.getIdUsuario());

            if (usuario instanceof Empresa) {
                EmpresaDAO dao = new EmpresaDAO();
                dao.update((Empresa) usuario);
            } else {
                ProfissionalDAO dao = new ProfissionalDAO();
                dao.update((Profissional) usuario);
            }
            statement.close();
            conn.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
 
    public void deletarUsuario(long userId) {
        String sql = "DELETE FROM Usuario WHERE id_usuario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, userId);
            statement.executeQuery();

            statement.close();
            conn.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
