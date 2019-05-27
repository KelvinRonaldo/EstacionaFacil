package br.senai.sp.estacionafacil.modelo;

import java.io.Serializable;

public class Telefone implements Serializable {

    private String telefone;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
