package org.example;

import org.example.Model.Equipamento;
import org.example.Service.AcaoCorretivaService;
import org.example.Service.EquipamentoService;
import org.example.Service.FalhaService;
import org.example.Service.RelatorioService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner imput = new Scanner(System.in);
        EquipamentoService equipamentoService = new EquipamentoService();
        FalhaService falhaService = new FalhaService();
        AcaoCorretivaService acaoCorretivaService = new AcaoCorretivaService();
        RelatorioService relatorioService = new RelatorioService();

        int opcao;
        do {
            System.out.println("\n==== SISTEMA DE GESTÃO DE QUALIDADE E MANUTENÇÃO ====");
            System.out.println("1. Cadastrar Equipamento");
            System.out.println("2. Listar Equipamentos");
            System.out.println("3. Registrar Falha");
            System.out.println("4. Registrar Ação Corretiva");
            System.out.println("5. Relatórios");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = imput.nextInt();
            imput.nextLine();

            switch (opcao){
                case 1 ->{
                    System.out.println("Nome do equipamento: ");
                    String nome = imput.nextLine();
                    System.out.println("Número da serie: ");
                    String serie = imput.nextLine();
                    System.out.println("Área/setor: ");
                    String setor = imput.nextLine();

                    Equipamento eq = new Equipamento(0, nome, serie, setor, "OPERACIONAL");
                }
            }
        }
    }
}