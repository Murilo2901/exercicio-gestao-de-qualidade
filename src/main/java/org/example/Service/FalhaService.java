package org.example.Service;

import org.example.Model.Equipamento;
import org.example.Model.Falha;
import org.example.Util.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FalhaService {
    private final EquipamentoService equipamentoService = new EquipamentoService();

    public Falha registrarNovaFalha(Falha falha) {
        Equipamento eq = equipamentoService.buscarEquipamentoPorId(falha.getEquipamentoId());
        if (eq == null) {
            throw new RuntimeException("Equipamento não encontrado!");
        }

        String sql = """
            INSERT INTO Falha (equipamentoId, dataHoraOcorrencia, descricao, criticidade, status, tempoParadaHoras)
            VALUES (?, ?, ?, ?, 'ABERTA', ?)
        """;
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, falha.getEquipamentoId());
            stmt.setTimestamp(2, Timestamp.valueOf(falha.getDataHoraOcorrencia()));
            stmt.setString(3, falha.getDescricao());
            stmt.setString(4, falha.getCriticidade());
            stmt.setDouble(5, falha.getTempoParadaHoras());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                falha.setId(rs.getLong(1));
            }

            if (falha.getCriticidade().equalsIgnoreCase("CRITICA")) {
                equipamentoService.atualizarStatusEquipamento(falha.getEquipamentoId(), "EM_MANUTENCAO");
            }

            return falha;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar falha: " + e.getMessage());
        }
    }

    public List<Falha> buscarFalhasCriticasAbertas() {
        String sql = "SELECT * FROM Falha WHERE status = 'ABERTA' AND criticidade = 'CRITICA'";
        List<Falha> lista = new ArrayList<>();

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Falha f = new Falha();
                f.setId(rs.getLong("id"));
                f.setEquipamentoId(rs.getLong("equipamentoId"));
                f.setDataHoraOcorrencia(rs.getTimestamp("dataHoraOcorrencia").toLocalDateTime());
                f.setDescricao(rs.getString("descricao"));
                f.setCriticidade(rs.getString("criticidade"));
                f.setStatus(rs.getString("status"));
                f.setTempoParadaHoras(rs.getDouble("tempoParadaHoras"));
                lista.add(f);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar falhas críticas: " + e.getMessage());
        }

        return lista;
    }
}
