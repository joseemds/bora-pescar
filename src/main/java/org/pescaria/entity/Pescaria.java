package org.pescaria.entity;

import java.util.Date;
import java.util.List;

public class Pescaria extends Entity {
    private Date data;
    private String local;
    private List<PeixeUnico> peixes;

    public Pescaria(Date data, String local, List<PeixeUnico> peixes) {
        this.data = data;
        this.local = local;
        this.peixes = peixes;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public List<PeixeUnico> getPeixes() {
        return peixes;
    }

    public void setPeixes(List<PeixeUnico> peixes) {
        this.peixes = peixes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pescaria pescaria = (Pescaria) o;
        return getId() == pescaria.getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pescaria{" + "id=" + getId() + ", data=" + data + ", local='" + local + '\'' + ", peixes=" + peixes
                + '}';
    }
}
