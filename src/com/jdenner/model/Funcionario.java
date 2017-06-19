package com.jdenner.model;

import java.security.InvalidParameterException;
import java.util.Date;

/**
 *
 * @author Juliano
 */
public class Funcionario extends Pessoa {

    private Date dataAdmissao;
    private Date dataDemissao;

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        if (dataAdmissao == null) {
            throw new InvalidParameterException("Data de admissão inválida.");
        }
        this.dataAdmissao = dataAdmissao;
    }

    public Date getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(Date dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

}
