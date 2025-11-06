package org.example.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class AcaoCorretiva {
    private Long id;
    private Long falhaId;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String responsavel;
    private String descricaoAcao;

    public AcaoCorretiva(Long id, Long falhaId, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String responsavel, String descricaoAcao) {
        this.id = id;
        this.falhaId = falhaId;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.responsavel = responsavel;
        this.descricaoAcao = descricaoAcao;
    }

    public Long getId() {
        return id;
    }

    public Long getFalhaId() {
        return falhaId;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public String getDescricaoAcao() {
        return descricaoAcao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFalhaId(Long falhaId) {
        this.falhaId = falhaId;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public void setDescricaoAcao(String descricaoAcao) {
        this.descricaoAcao = descricaoAcao;
    }
}
