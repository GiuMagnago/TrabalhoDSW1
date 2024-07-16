package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;

public class ProfissionalDAO extends GenericDAO {

    public void insert(Profissional profissional) {

        String sql = "INSERT INTO Profissional Profissional(email, senha, nome, sexo, cpf, telefone, datanasc) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getEmail());
            statement.setString(2, profissional.getSenha());
            statement.setString(3, profissional.getnome());
            statement.setCharacterStream(4, profissional.getSexo());
            statement.setString(5, profissional.getCPF());
            statement.setString(6, profissional.getTelefone());
            statement.setString(7, profissional.getDataNasc(),toString());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Profissional> getAll() {

        List<Profissional> listaLivros = new ArrayList<>();

        String sql = "SELECT * from Livro l, Editora e where l.EDITORA_ID = e.ID order by l.id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                int ano = resultSet.getInt("ano");
                float preco = resultSet.getFloat("preco");
                Long editora_id = resultSet.getLong(6);
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                Empresa editora = new Empresa(editora_id, cnpj, nome);
                Profissional profissional = new Profissional(id, titulo, autor, ano, preco, editora);
                listaLivros.add(profissional);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaLivros;
    }

    public void delete(Profissional profissional) {
        String sql = "DELETE FROM Livro where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, profissional.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Profissional profissional) {
        String sql = "UPDATE Livro SET titulo = ?, autor = ?, ano = ?, preco = ?";
        sql += ", editora_id = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getTitulo());
            statement.setString(2, profissional.getAutor());
            statement.setInt(3, profissional.getAno());
            statement.setFloat(4, profissional.getPreco());
            statement.setFloat(5, profissional.getEditora().getId());
            statement.setLong(6, profissional.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Profissional get(Long id) {
        Profissional profissional = null;

        String sql = "SELECT * from Livro l, Editora e where l.id = ? and l.EDITORA_ID = e.ID";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                int ano = resultSet.getInt("ano");
                float preco = resultSet.getFloat("preco");

                Long editoraID = resultSet.getLong("editora_id");
                Empresa editora = new EmpresaDAO().get(editoraID);

                profissional = new Profissional(id, titulo, autor, ano, preco, editora);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }

    public int countByEditora(Long id) {
        int contador = 0;
        
        String sql = "SELECT count(*) from Livro l where l.EDITORA_ID = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                contador = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contador;
    }
}
