package org.example.Model;

import java.time.LocalDateTime;

public class Falha {
    private Long id;
    private Long equipamentoId;
    private LocalDateTime dataHoraOcorrencia;
    private String descricao;
    private String criticidade;
    private String status;
    private double tempoParadaHoras;

    public Falha() {
        this.id = id;
        this.equipamentoId = equipamentoId;
        this.dataHoraOcorrencia = dataHoraOcorrencia;
        this.descricao = descricao;
        this.criticidade = criticidade;
        this.status = status;
        this.tempoParadaHoras = tempoParadaHoras;
    }

    public Long getId() {
        return id;
    }

    public Long getEquipamentoId() {
        return equipamentoId;
    }

    public LocalDateTime getDataHoraOcorrencia() {
        return dataHoraOcorrencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCriticidade() {
        return criticidade;
    }

    public String getStatus() {
        return status;
    }

    public double getTempoParadaHoras() {
        return tempoParadaHoras;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEquipamentoId(Long equipamentoId) {
        this.equipamentoId = equipamentoId;
    }

    public void setDataHoraOcorrencia(LocalDateTime dataHoraOcorrencia) {
        this.dataHoraOcorrencia = dataHoraOcorrencia;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCriticidade(String criticidade) {
        this.criticidade = criticidade;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTempoParadaHoras(double tempoParadaHoras) {
        this.tempoParadaHoras = tempoParadaHoras;
    }
}
