package org.example.Service;

import org.example.Model.Equipamento;
import org.example.Util.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipamentoService {
    public Equipamento criarEquipamento(Equipamento equipamento)throws SQLException {
        String sql = """
                INSERT INTO equipamento
                (nome, numeroDeSerie, areaSetor, statusOperacional)
                VALUES
                (?,?,?,?)
                """;
        try(Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,equipamento.getNome());
            stmt.setString(2,equipamento.getNumeroDeSerie());
            stmt.setString(3,equipamento.getAreaSetor());
            stmt.setString(4,equipamento.getStatusOperacional());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                equipamento.setId(rs.getLong(1));
            }
            return equipamento;
        }catch (SQLException e){
            throw new RuntimeException("Erro ao criar equipamento" + e.getMessage());
        }
    }

    public Equipamento buscarEquipamentoPorId(long id) {
        String sql = "SELECT * FROM Equipamento WHERE id = ?";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Equipamento eq = new Equipamento();
                eq.setId(rs.getLong("id"));
                eq.setNome(rs.getString("nome"));
                eq.setNumeroDeSerie(rs.getString("numeroDeSerie"));
                eq.setAreaSetor(rs.getString("areaSetor"));
                eq.setStatusOperacional(rs.getString("statusOperacional"));
                return eq;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar equipamento: " + e.getMessage());
        }
    }

    public static void atualizarStatusEquipamento(long id, String novoStatus) {
        String sql = "UPDATE Equipamento SET statusOperacional = ? WHERE id = ?";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoStatus);
            stmt.setLong(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status do equipamento: " + e.getMessage());
        }
    }
}
