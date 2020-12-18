package br.edu.ifpb.testes.agenda.contato;

import java.util.Objects;

public class Contato  {

    private long id;
    private String nome;
    private String telefone;

    public Contato() {
        this.id = 0;
        this.nome=null;
        this.telefone=null;
    }

    public Contato(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
        this.id=0;
    }

    public Contato(long id) {
        this.id = id;
        this.telefone=null;
        this.nome=null;
    }

    public Contato(long id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return id == contato.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
