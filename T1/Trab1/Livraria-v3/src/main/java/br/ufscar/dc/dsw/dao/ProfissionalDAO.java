package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Profissional;

public class ProfissionalDAO extends GenericDAO {

    public void insert(long userId, Profissional profissional) {

        String sql = "INSERT INTO Profissional Profissional(id_usuario, cpf, nome, telefone, sexo, datanasc) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, userId);
            statement.setString(2, profissional.getCPF());
            statement.setString(3, profissional.getNome());
            statement.setString(4, profissional.getTelefone());
            statement.setString(5, profissional.getSexo());
            statement.setDate(6, new java.sql.Date(profissional.getDataNasc().getTime())); //conversão de java.util.Date para java.sql.Date
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Profissional> getAll() {

        List<Profissional> listaProfissionais = new ArrayList<>();
        String sql = "SELECT * from Profissional JOIN Usuario ON Usuario.id_usuario = Profissional.id_usuario";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long idUsuario = resultSet.getLong("id_usuario");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                Date dataNasc = resultSet.getDate("datanasc");
                Profissional profissional = new Profissional(idUsuario, email, senha, cpf, nome, telefone, sexo, dataNasc);
                listaProfissionais.add(profissional);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissionais;
    }

    public void update(Profissional profissional) {
        String sql = "UPDATE Profissional SET cpf = ?, nome = ?, telefone = ?, sexo = ?, datanasc = ? WHERE id_usuario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getCPF());
            statement.setString(2, profissional.getNome());
            statement.setString(3, profissional.getTelefone());
            statement.setString(4, profissional.getSexo());
            statement.setDate(5, new java.sql.Date(profissional.getDataNasc().getTime())); //conversão de java.util.Date para java.sql.Date
            statement.setLong(6, profissional.getIdUsuario());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Profissional get(Long idUsuario, String email, String senha) {
        Profissional profissional = null;

        String sql = "SELECT * from Profissional WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, idUsuario);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id_profissional");
                String nome = resultSet.getString("nome");
                String sexo = resultSet.getString("sexo");
                String cpf = resultSet.getString("cpf");
                String telefone = resultSet.getString("telefone");
                Date dataNasc = resultSet.getDate("datanasc");
                profissional = new Profissional(idUsuario, email, senha, cpf, nome, telefone, sexo, dataNasc);
                profissional.setIdProfissional(id);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }
}
