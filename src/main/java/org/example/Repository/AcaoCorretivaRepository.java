package org.example.Repository;

import org.example.Model.AcaoCorretiva;
import org.example.Util.ConexaoBanco;

import java.sql.*;

public class AcaoCorretivaRepository {

    public AcaoCorretiva save(AcaoCorretiva acao) throws SQLException {
        String sql = """
            INSERT INTO AcaoCorretiva (falhaId, dataHoraInicio, dataHoraFim, responsavel, descricaoAcao)
            VALUES (?, ?, ?, ?, ?)
        """;
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, acao.getFalhaId());
            stmt.setTimestamp(2, Timestamp.valueOf(acao.getDataHoraInicio()));
            stmt.setTimestamp(3, Timestamp.valueOf(acao.getDataHoraFim()));
            stmt.setString(4, acao.getResponsavel());
            stmt.setString(5, acao.getDescricaoAcao());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) acao.setId(rs.getLong(1));
            return acao;
        }
    }
}
