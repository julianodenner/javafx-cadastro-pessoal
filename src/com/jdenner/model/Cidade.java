package com.jdenner.model;

import java.security.InvalidParameterException;

/**
 *
 * @author Juliano
 */
public class Cidade {

    private int codCidade;
    private String nome;
    private Estado estado;
    private Status status;

    public int getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(int codCidade) {
        if (codCidade < 0) {
            throw new InvalidParameterException("Código inválido.");
        }
        this.codCidade = codCidade;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
        if (obj instanceof Cidade) {
            return ((Cidade) obj).getCodCidade() == getCodCidade();
        }
        return false;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
