package br.senai.sp.estacionafacil.modelo;

import java.io.Serializable;

public class Telefone implements Serializable {

    private Integer codTelefone;
    private String telefone;

    public Integer getCodTelefone() {
        return codTelefone;
    }

    public void setCodTelefone(Integer codTelefone) {
        this.codTelefone = codTelefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
