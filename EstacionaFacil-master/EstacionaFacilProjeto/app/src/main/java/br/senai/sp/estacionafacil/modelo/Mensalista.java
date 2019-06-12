package br.senai.sp.estacionafacil.modelo;

import java.io.Serializable;

public class Mensalista implements Serializable {
    private int codMensalista;
    private String nome;
    private String email;
    private String cpf;

    public int getCodMensalista() {
        return codMensalista;
    }

    public void setCodMensalista(int codMensalista) {
        this.codMensalista = codMensalista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return codMensalista + " - " + nome + " - " + email + " - " + cpf;
    }
}
