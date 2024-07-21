package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufscar.dc.dsw.domain.Vaga;

public class VagaDAO extends GenericDAO{

    public void insert(Vaga vaga) {
        String sql = "INSERT INTO Vaga (id_empresa, cnpj_empresa, descricao, remuneracao, dataLimite,) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, vaga.getIdEmpresa());
            statement.setString(2, vaga.getCnpjEmpresa());
            statement.setString(3, vaga.getDescricao());
            statement.setDouble(4, vaga.getRemuneracao());
            statement.setDate(5, new java.sql.Date(vaga.getDataLimite().getTime()));
            statement.executeUpdate();

            statement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vaga> getAllOrderByCidade() {
        List<Vaga> listaVagas = new ArrayList<>();
        String sql = "SELECT * FROM Vaga JOIN Empresa ON Empresa.id_empresa = Vaga.id_empresa ORDER BY Empresa.cidade";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                if (resultSet.getDate("dataLimite").compareTo(new Date()) > 0) {
                    long id_vaga = resultSet.getLong("id_vaga");
                    long id_empresa = resultSet.getLong("id_empresa");
                    String cnpj_empresa = resultSet.getString("cnpj_empresa");
                    String descricao = resultSet.getString("descricao");
                    double remuneracao = resultSet.getDouble("remuneracao");
                    Date dataLimite = resultSet.getDate("dataLimite");
    
                    Vaga vaga = new Vaga(id_vaga, id_empresa, cnpj_empresa, descricao, remuneracao, dataLimite);
                    listaVagas.add(vaga);
                }
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaVagas;
    }

    public List<Vaga> getAllFromEmpresa(long id_empresa) {
        List<Vaga> listaVagas = new ArrayList<>();
        String sql = "SELECT * FROM Vaga JOIN Empresa ON Empresa.id_empresa = Vaga.id_empresa WHERE id_empresa = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id_empresa);

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                long id_vaga = resultSet.getLong("id_vaga");
                String cnpj_empresa = resultSet.getString("cnpj_empresa");
                String descricao = resultSet.getString("descricao");
                double remuneracao = resultSet.getDouble("remuneracao");
                Date dataLimite = resultSet.getDate("dataLimite");

                Vaga vaga = new Vaga(id_vaga, id_empresa, cnpj_empresa, descricao, remuneracao, dataLimite);
                listaVagas.add(vaga);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaVagas;
    }

    public Vaga get(long id_vaga) {
        Vaga vaga = null;
        String sql = "SELECT * FROM Vaga WHERE id_vaga = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id_vaga);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                long id_empresa = resultSet.getLong("id_empresa");
                String cnpj_empresa = resultSet.getString("cnpj_empresa");
                String descricao = resultSet.getString("descricao");
                double remuneracao = resultSet.getDouble("remuneracao");
                Date dataLimite = resultSet.getDate("dataLimite");

                vaga = new Vaga(id_vaga, id_empresa, cnpj_empresa, descricao, remuneracao, dataLimite);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vaga;
    }

    public void delete(long id_vaga) {
        String sql = "DELETE FROM Vaga WHERE id_vaga = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id_vaga);
            statement.executeUpdate();
            
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
