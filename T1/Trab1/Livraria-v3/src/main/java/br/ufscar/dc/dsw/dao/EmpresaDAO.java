package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;

public class EmpresaDAO extends GenericDAO {

    public void insert(long userId, Empresa empresa) {

        String sql = "INSERT INTO Empresa (id_usuario, cnpj, nome, descricao, cidade) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setLong(1, userId);
            statement.setString(2, empresa.getCNPJ());
            statement.setString(3, empresa.getNome());
            statement.setString(4, empresa.getDescricao());
            statement.setString(5, empresa.getCidade());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Empresa> getAll() {

        List<Empresa> listaEmpresas = new ArrayList<>();

        String sql = "SELECT * FROM Empresa JOIN Usuario ON Usuario.id_usuario = Empresa.id_usuario";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_usuario");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                String cidade = resultSet.getString("cidade");
                Empresa empresa = new Empresa(id, email, senha, cnpj, nome, descricao, cidade);
                listaEmpresas.add(empresa);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEmpresas;
    }

    public void update(Empresa empresa) {
        String sql = "UPDATE Empresa SET cnpj = ?, nome = ?, descricao = ?, cidade = ? WHERE id_usuario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, empresa.getCNPJ());
            statement.setString(2, empresa.getNome());
            statement.setString(3, empresa.getDescricao());
            statement.setString(4, empresa.getCidade());
            statement.setLong(5, empresa.getIdUsuario());
            
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Empresa get(Long idUsuario, String email, String senha) {
        Empresa empresa = null;
        
        String sql = "SELECT * from Empresa where id_usuario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setLong(1, idUsuario);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long idEmpresa = resultSet.getLong("id_empresa");
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                String cidade = resultSet.getString("cidade");
                empresa = new Empresa(idUsuario, email, senha, cnpj, nome, descricao, cidade);
                empresa.setIdEmpresa(idEmpresa);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return empresa;
    }
}