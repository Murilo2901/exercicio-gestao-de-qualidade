package org.example.DTO;

import org.example.Model.AcaoCorretiva;
import org.example.Model.Equipamento;
import org.example.Model.Falha;

import java.util.List;

public class FalhaDetalhadaDTO {
    private Falha falha;
    private Equipamento equipamento;
    private List<AcaoCorretiva> acaoCorretivas;

    public FalhaDetalhadaDTO(Falha falha, Equipamento equipamento, List<AcaoCorretiva> acaoCorretivas) {
        this.falha = falha;
        this.equipamento = equipamento;
        this.acaoCorretivas = acaoCorretivas;
    }

    public Falha getFalha() {
        return falha;
    }

    public void setFalha(Falha falha) {
        this.falha = falha;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public List<AcaoCorretiva> getAcaoCorretivas() {
        return acaoCorretivas;
    }

    public void setAcaoCorretivas(List<AcaoCorretiva> acaoCorretivas) {
        this.acaoCorretivas = acaoCorretivas;
    }
}
