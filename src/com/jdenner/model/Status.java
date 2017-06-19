package com.jdenner.model;

/**
 *
 * @author Juliano
 */
public enum Status {

    A("A", "Ativo"),
    I("I", "Inativo");

    private String codStatus;
    private String descricao;

    public String getCodStatus() {
        return codStatus;
    }

    public void setCodStatus(String codStatus) {
        this.codStatus = codStatus;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private Status(String codStatus, String descricao) {
        this.codStatus = codStatus;
        this.descricao = descricao;
    }

    public static Status get(String obj) {
        for (Status status : values()) {
            if (status.getCodStatus().equals(obj)) {
                return status;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getDescricao();
    }

}
