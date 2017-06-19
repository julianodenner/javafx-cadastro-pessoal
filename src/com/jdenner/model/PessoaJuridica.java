package com.jdenner.model;

import java.security.InvalidParameterException;

/**
 *
 * @author Juliano
 */
public class PessoaJuridica {

    private int codPessoaJuridica;
    private String cnpj;
    private String ie;
    private String razaoSocial;

    public int getCodPessoaJuridica() {
        return codPessoaJuridica;
    }

    public void setCodPessoaJuridica(int codPessoaJuridica) {
        if (codPessoaJuridica < 0) {
            throw new InvalidParameterException("Código inválido.");
        }
        this.codPessoaJuridica = codPessoaJuridica;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (cnpj.trim().length() != 18) {
            throw new InvalidParameterException("CNPJ inválido.");
        }
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

}
