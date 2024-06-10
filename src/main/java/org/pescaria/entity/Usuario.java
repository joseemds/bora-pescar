package org.pescaria.entity;

public class Usuario extends Entity {
    private String nome;
    private String senha;
    private TipoUsuario tipo;

    public Usuario(String nome, String senha, TipoUsuario tipo) {
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Usuario usuario = (Usuario) o;
        return getId() == usuario.getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + getId() + ", nome='" + nome + '\'' + ", tipo=" + tipo + '}';
    }
}
