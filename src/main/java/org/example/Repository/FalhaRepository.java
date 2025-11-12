package org.example.Repository;

import org.example.Model.Falha;
import org.example.Util.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FalhaRepository {

    public Falha save(Falha falha) throws SQLException {
        String sql = """
            INSERT INTO Falha (equipamentoId, dataHoraOcorrencia, descricao, criticidade, status, tempoParadaHoras)
            VALUES (?, ?, ?, ?, ?, ?)
        """;
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, falha.getEquipamentoId());
            stmt.setTimestamp(2, Timestamp.valueOf(falha.getDataHoraOcorrencia()));
            stmt.setString(3, falha.getDescricao());
            stmt.setString(4, falha.getCriticidade());
            stmt.setString(5, falha.getStatus());
            stmt.setDouble(6, falha.getTempoParadaHoras());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) falha.setId(rs.getLong(1));
            return falha;
        }
    }

    public Falha findById(long id) throws SQLException {
        String sql = "SELECT * FROM Falha WHERE id = ?";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Falha f = new Falha();
                f.setId(rs.getLong("id"));
                f.setEquipamentoId(rs.getLong("equipamentoId"));
                f.setDescricao(rs.getString("descricao"));
                f.setCriticidade(rs.getString("criticidade"));
                f.setStatus(rs.getString("status"));
                f.setTempoParadaHoras(rs.getDouble("tempoParadaHoras"));
                f.setDataHoraOcorrencia(rs.getTimestamp("dataHoraOcorrencia").toLocalDateTime());
                return f;
            }
        }
        return null;
    }

    public void updateStatus(long id, String novoStatus) throws SQLException {
        String sql = "UPDATE Falha SET status = ? WHERE id = ?";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoStatus);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    public List<Falha> findFalhasCriticasAbertas() throws SQLException {
        String sql = "SELECT * FROM Falha WHERE status = 'ABERTA' AND criticidade = 'CRITICA'";
        List<Falha> lista = new ArrayList<>();

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Falha f = new Falha();
                f.setId(rs.getLong("id"));
                f.setEquipamentoId(rs.getLong("equipamentoId"));
                f.setDescricao(rs.getString("descricao"));
                f.setCriticidade(rs.getString("criticidade"));
                f.setStatus(rs.getString("status"));
                f.setTempoParadaHoras(rs.getDouble("tempoParadaHoras"));
                lista.add(f);
            }
        }
        return lista;
    }
}
