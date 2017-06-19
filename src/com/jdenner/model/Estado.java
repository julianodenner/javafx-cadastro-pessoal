package com.jdenner.model;

import java.security.InvalidParameterException;

/**
 *
 * @author Juliano
 */
public class Estado {

    private int codEstado;
    private String nome;
    private String sigla;
    private Status status = Status.A;

    public int getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(int codEstado) {
        if (codEstado < 0) {
            throw new InvalidParameterException("Código inválido.");
        }
        this.codEstado = codEstado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.trim().length() < 3 || nome.trim().length() > 200) {
            throw new InvalidParameterException("Nome inválido.");
        }
        this.nome = nome.trim();
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        if (sigla.trim().length() != 2) {
            throw new InvalidParameterException("Sigla inválida.");
        }
        this.sigla = sigla.trim().toUpperCase();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = Status.get(status);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Estado) {
            return ((Estado) obj).getCodEstado() == getCodEstado();
        }
        return false;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
