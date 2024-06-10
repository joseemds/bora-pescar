package org.pescaria.entity;

public class PeixeUnico extends Entity {
    private Peixe peixe;
    private double peso;
    private double tamanho;

    // Construtor sem id, pois o id é gerado automaticamente pelo banco de dados
    public PeixeUnico(Peixe peixe, double peso, double tamanho) {
        this.peixe = peixe;
        this.peso = peso;
        this.tamanho = tamanho;
    }

    // Getters e Setters
    public Peixe getPeixe() {
        return peixe;
    }

    public void setPeixe(Peixe peixe) {
        this.peixe = peixe;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    // comparar PeixeUnico pelo id
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PeixeUnico that = (PeixeUnico) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PeixeUnico{" +
                "id=" + getId() +
                ", peixe=" + peixe +
                ", peso=" + peso +
                ", tamanho=" + tamanho +
                '}';
    }
}
