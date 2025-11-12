package org.example.Service;

import org.example.Model.Falha;
import org.example.Repository.EquipamentoRepository;
import org.example.Repository.FalhaRepository;

public class FalhaService {
    private final EquipamentoRepository equipamentoRepo = new EquipamentoRepository();
    private final FalhaRepository falhaRepo = new FalhaRepository();

    public Falha registrarNovaFalha(Falha falha) {
        try {
            var equipamento = equipamentoRepo.findById(falha.getEquipamentoId());
            if (equipamento == null)
                throw new RuntimeException("Equipamento não encontrado!");

            falha.setStatus("ABERTA");
            falhaRepo.save(falha);

            if (falha.getCriticidade().equalsIgnoreCase("CRITICA")) {
                equipamentoRepo.updateStatus(falha.getEquipamentoId(), "EM_MANUTENCAO");
            }

            return falha;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao registrar falha: " + e.getMessage());
        }
    }
}
