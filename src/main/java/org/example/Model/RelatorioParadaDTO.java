package org.example.Model;

public class RelatorioParadaDTO {
    private String nomeEquipamento;
    private double totalParadaHora;

    public RelatorioParadaDTO(String nomeEquipamento, double totalParadaHora) {
        this.nomeEquipamento = nomeEquipamento;
        this.totalParadaHora = totalParadaHora;
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public double getTotalParadaHora() {
        return totalParadaHora;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }

    public void setTotalParadaHora(double totalParadaHora) {
        this.totalParadaHora = totalParadaHora;
    }
}
