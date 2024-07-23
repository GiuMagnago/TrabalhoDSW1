package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Candidatura;

public class CandidaturaDAO extends GenericDAO {
    public void insert(Candidatura candidatura) {
        String sql = "INSERT INTO Candidatura (id_profissional, id_vaga, statusCandidatura) VALUES (?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, candidatura.getIdProfissional());
            statement.setLong(2, candidatura.getIdVaga());
            statement.setString(3, candidatura.getStatusCandidatura());
            statement.executeUpdate();
            
            statement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Candidatura candidatura) {
        String sql = "UPDATE Candidatura SET status_candidatura = ? WHERE id_profissional = ? AND id_vaga = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, candidatura.getStatusCandidatura());
            statement.setLong(2, candidatura.getIdProfissional());
            statement.setLong(3, candidatura.getIdVaga());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public class CandidaturaProfissionalView {
        public long id_vaga;
        public String nome_empresa;
        public String cnpj_empresa;
        public String status;

        public CandidaturaProfissionalView(long id_vaga, String nome_empresa, String cnpj_empresa, String status) {
            this.id_vaga = id_vaga;
            this.nome_empresa = nome_empresa;
            this.cnpj_empresa = cnpj_empresa;
            this.status = status;
        }
    }
    
    public List<CandidaturaProfissionalView> getAllFromProfissional(long id_profissional) {
        List<CandidaturaProfissionalView> listaCandidaturaProfissionalView = new ArrayList<>();
        String sql = "SELECT * FROM Candidatura JOIN Vaga ON Vaga.id_vaga = Candidatura.id_vaga JOIN Empresa ON Vaga.cnpj_empresa = Empresa.cnpj WHERE id_profissional = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id_profissional);

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id_vaga = resultSet.getLong("Candidatura.id_vaga");
                String nome_empresa = resultSet.getString("Empresa.nome");
                String cnpj_empresa = resultSet.getString("Empresa.cnpj");
                String statusCandidatura = resultSet.getString("Candidatura.statusCandidatura");

                CandidaturaProfissionalView candidaturaProfissionalView = new CandidaturaProfissionalView(id_vaga, nome_empresa, cnpj_empresa, statusCandidatura);
                listaCandidaturaProfissionalView.add(candidaturaProfissionalView);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaCandidaturaProfissionalView;
    }

    public class CandidaturaEmpresaView {
        public long id_vaga;
        public long id_profissional;
        public String nome_profissional;
        public String status;

        public CandidaturaEmpresaView(long id_vaga, long id_profissional, String nome_profissional, String status) {
            this.id_vaga = id_vaga;
            this.id_profissional = id_profissional;
            this.nome_profissional = nome_profissional;
            this.status = status;
        }
    }

    public List<CandidaturaEmpresaView> getAllFromVaga(long id_vaga) {
        List<CandidaturaEmpresaView> listaCandidaturaEmpresaViews = new ArrayList<>();
        String sql = "SELECT * FROM Candidatura JOIN Profissional ON Profissional.id_profissional = Candidatura.id_profissional WHERE id_vaga = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id_vaga);

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id_profissional = resultSet.getLong("Candidatura.id_profissional");
                String nome_profissional = resultSet.getString("Profissional.nome");
                String statusCandidatura = resultSet.getString("Candidatura.statusCandidatura");

                CandidaturaEmpresaView candidaturaEmpresaView = new CandidaturaEmpresaView(id_vaga, id_profissional, nome_profissional, statusCandidatura);
                listaCandidaturaEmpresaViews.add(candidaturaEmpresaView);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaCandidaturaEmpresaViews;
    }

}
