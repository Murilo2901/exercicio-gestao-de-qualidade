package org.example.Model;

public class Equipamento {
    private Long id;
    private String nome;
    private String numeroDeSerie;
    private String areaSetor;
    private String statusOperacional;

    public Equipamento() {
        this.id = id;
        this.nome = nome;
        this.areaSetor = areaSetor;
        this.numeroDeSerie = numeroDeSerie;
        this.statusOperacional = statusOperacional;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public String getAreaSetor() {
        return areaSetor;
    }

    public String getStatusOperacional() {
        return statusOperacional;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public void setAreaSetor(String areaSetor) {
        this.areaSetor = areaSetor;
    }

    public void setStatusOperacional(String statusOperacional) {
        this.statusOperacional = statusOperacional;
    }
}
