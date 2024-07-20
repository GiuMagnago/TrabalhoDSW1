package br.ufscar.dc.dsw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcessaBD {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Sistema", "aluno", "aluno");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Profissional");
            while (rs.next()) {
                System.out.print(rs.getLong("id"));
                System.out.print(", " + rs.getString("email"));
                System.out.print(", " + rs.getString("senha"));
                System.out.print(", " + rs.getString("nome"));
                System.out.print(", " + rs.getString("sexo"));
                System.out.print(", " + rs.getString("cpf"));
                System.out.print(", " + rs.getString("telefone"));
                System.out.println(", " + rs.getString("datanasc"));
            }

            System.out.println();

            rs = stmt.executeQuery("select * from Empresa");
            while (rs.next()) {
                System.out.print(rs.getLong("id"));
                System.out.print(", " + rs.getString("email"));
                System.out.print(", " + rs.getString("senha"));
                System.out.print(", " + rs.getString("cnpj"));
                System.out.print(", " + rs.getString("nome"));
                System.out.print(", " + rs.getString("descricao"));
                System.out.println(", " + rs.getString("cidade"));
            }
            stmt.close();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("A classe do driver de conexão não foi encontrada!");
        } catch (SQLException e) {
            System.out.println("O comando SQL não pode ser executado!");
        }
    }
}