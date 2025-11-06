package org.example.Service;

import org.example.Model.AcaoCorretiva;
import org.example.Model.Falha;
import org.example.Util.ConexaoBanco;

import java.sql.*;

public class AcaoCorretivaService {
    private final FalhaService falhaService = new FalhaService();
    private final EquipamentoService equipamentoService = new EquipamentoService();

    public AcaoCorretiva registrarConclusaoDeAcao(AcaoCorretiva acaoCorretiva)throws SQLException {
        Falha falha = buscarFalhaPorId(acaoCorretiva.getFalhaId());
        if (falha == null){
            throw new RuntimeException("Falha não encontrada");
        }
        String sql = """
                INSERT INTO acaoCorretiva
                (falhaId, dataHoraInicio, dataHoraFim, responsavel, descricaoAcao)
                VALUES
                (?,?,?,?,?)
                """;
        try(Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1,acaoCorretiva.getFalhaId());
            stmt.setTimestamp(2,Timestamp.valueOf(acaoCorretiva.getDataHoraInicio()));
            stmt.setTimestamp(3,Timestamp.valueOf(acaoCorretiva.getDataHoraFim()));
            stmt.setString(4,acaoCorretiva.getResponsavel());
            stmt.setString(5,acaoCorretiva.getDescricaoAcao());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                acaoCorretiva.setId(rs.getLong(1));
            }

            atualizarStatusFalha(falha.getId(), "RESOLVIDA");

            if(falha.getCriticidade().equalsIgnoreCase("CRITICA")){
                EquipamentoService.atualizarStatusEquipamento(falha.getEquipamentoId(),("OPERACIONAL"));
            }
            return acaoCorretiva;
        }catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar ação corretiva: " + e.getMessage());
        }
    }

    private Falha buscarFalhaPorId(Long id)throws SQLException{
        String sql = """
                SELECT*FROM falha
                WHERE id = ?
                """;
        try(Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                Falha f = new Falha();
                f.setId(rs.getLong("id"));
                f.setEquipamentoId(rs.getLong("equipamento"));
                f.setCriticidade(rs.getString("critividade"));
                return f;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private void atualizarStatusFalha(long id, String novoStatus)throws SQLException{
        String sql = """
                UPDATE falha
                SET status = ?
                WHERE id = ?
                """;
        try(Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1,id);
            stmt.setString(2,novoStatus);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Erro ao atualizar falha" + e.getMessage());
        }
    }
}
