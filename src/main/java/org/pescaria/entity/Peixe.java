package org.pescaria.entity;

public class Peixe extends Entity {
    private String especie;
    private String descricao;

    public Peixe(String especie, String descricao) {
        this.especie = especie;
        this.descricao = descricao;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // comparar peixes pelo id
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Peixe peixe = (Peixe) o;
        return getId() == peixe.getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Peixe{" +
                "id=" + getId() +
                ", especie='" + especie + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
