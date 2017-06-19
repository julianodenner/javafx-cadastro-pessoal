package com.jdenner.model;

import java.security.InvalidParameterException;
import java.util.Date;

/**
 *
 * @author Juliano
 */
public class PessoaFisica {

    private int codPessoaFisica;
    private String cpf;
    private String rg;
    private Date dataNascimento;

    public int getCodPessoaFisica() {
        return codPessoaFisica;
    }

    public void setCodPessoaFisica(int codPessoaFisica) {
        if (codPessoaFisica < 0) {
            throw new InvalidParameterException("Código inválido.");
        }
        this.codPessoaFisica = codPessoaFisica;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf.trim().length() != 14) {
            throw new InvalidParameterException("CPF inválido.");
        }
        //TODO Validar CPF...
        this.cpf = cpf.trim();
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        if (dataNascimento == null || dataNascimento.after(new Date())) {
            throw new InvalidParameterException("Data de nascimento inválida.");
        }
        this.dataNascimento = dataNascimento;
    }

}
