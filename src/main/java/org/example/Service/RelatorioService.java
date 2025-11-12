package org.example.Service;

import org.example.DTO.RelatorioParadaDTO;
import org.example.Model.Equipamento;
import org.example.Model.EquipamentoContagemFalhasDTO;
import org.example.Util.ConexaoBanco;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RelatorioService {

    public List<RelatorioParadaDTO> gerarRelatorioTempoParada() {
        String sql = """
            SELECT e.nome, SUM(f.tempoParadaHoras) AS totalParada
            FROM Falha f
            JOIN Equipamento e ON e.id = f.equipamentoId
            GROUP BY e.id
        """;

        List<RelatorioParadaDTO> lista = new ArrayList<>();

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                RelatorioParadaDTO dto = new RelatorioParadaDTO();
                dto.setNomeEquipamento(rs.getString("nome"));
                dto.setTotalParadaHora(rs.getDouble("totalParada"));
                lista.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao gerar relatório: " + e.getMessage());
        }

        return lista;
    }

    public List<Equipamento> buscarEquipamentosSemFalhasPorPeriodo(LocalDate inicio, LocalDate fim) {
        String sql = """
            SELECT * FROM Equipamento e
            WHERE e.id NOT IN (
                SELECT f.equipamentoId FROM Falha f
                WHERE f.dataHoraOcorrencia BETWEEN ? AND ?
            )
        """;

        List<Equipamento> lista = new ArrayList<>();

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(inicio));
            stmt.setDate(2, Date.valueOf(fim));

            try (ResultSet rs = stmt.executeQuery()) {
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

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar equipamentos: " + e.getMessage());
        }

        return lista;
    }

    public List<EquipamentoContagemFalhasDTO> gerarRelatorioManutencaoPreventiva(int contagemMinima) {
        if (contagemMinima <= 0) {
            throw new RuntimeException("Valor mínimo inválido!");
        }

        String sql = """
            SELECT e.nome, COUNT(f.id) AS totalFalhas
            FROM Falha f
            JOIN Equipamento e ON e.id = f.equipamentoId
            GROUP BY e.id
            HAVING COUNT(f.id) >= ?
        """;

        List<EquipamentoContagemFalhasDTO> lista = new ArrayList<>();

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contagemMinima);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    EquipamentoContagemFalhasDTO dto = new EquipamentoContagemFalhasDTO();
                    dto.setNomeEquipamento(rs.getString("nome"));
                    dto.setTotalFalhas(rs.getLong("totalFalhas"));
                    lista.add(dto);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao gerar relatório: " + e.getMessage());
        }

        return lista;
    }
}