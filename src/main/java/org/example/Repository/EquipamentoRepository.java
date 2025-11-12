package org.example.Repository;

import org.example.Model.Equipamento;
import org.example.Util.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoRepository {

    public Equipamento save(Equipamento equipamento) throws SQLException {
        String sql = """
            INSERT INTO Equipamento (nome, numeroDeSerie, areaSetor, statusOperacional)
            VALUES (?, ?, ?, ?)
        """;
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroDeSerie());
            stmt.setString(3, equipamento.getAreaSetor());
            stmt.setString(4, equipamento.getStatusOperacional());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                equipamento.setId(rs.getLong(1));
            }
            return equipamento;
        }
    }

    public Equipamento findById(long id) throws SQLException {
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
        }
        return null;
    }

    public void updateStatus(long id, String novoStatus) throws SQLException {
        String sql = "UPDATE Equipamento SET statusOperacional = ? WHERE id = ?";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoStatus);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    public List<Equipamento> findAll() throws SQLException {
        String sql = "SELECT * FROM Equipamento";
        List<Equipamento> lista = new ArrayList<>();
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Equipamento eq = new Equipamento();
                eq.setId(rs.getLong("id"));
                eq.setNome(rs.getString("nome"));
                eq.setNumeroDeSerie(rs.getString("numeroDeSerie"));
                eq.setAreaSetor(rs.getString("areaSetor"));
                eq.setStatusOperacional(rs.getString("statusOperacional"));
                lista.add(eq);
            }
        }
        return lista;
    }
}
