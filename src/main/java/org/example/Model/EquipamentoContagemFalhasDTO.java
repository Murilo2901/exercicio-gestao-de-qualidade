package org.example.Model;

public class EquipamentoContagemFalhasDTO {
    private String nomeEquipamento;
    private long totalFalhas;

    public EquipamentoContagemFalhasDTO(String nomeEquipamento, long totalFalhas) {
        this.nomeEquipamento = nomeEquipamento;
        this.totalFalhas = totalFalhas;
    }

    public EquipamentoContagemFalhasDTO() {

    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }

    public long getTotalFalhas() {
        return totalFalhas;
    }

    public void setTotalFalhas(long totalFalhas) {
        this.totalFalhas = totalFalhas;
    }
}
