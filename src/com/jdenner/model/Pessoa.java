package com.jdenner.model;

import java.security.InvalidParameterException;
import java.util.Date;

/**
 *
 * @author Juliano
 */
public class Pessoa {

    private int codPessoa;
    private PessoaFisica pessoaFisica;
    private PessoaJuridica pessoaJuridica;
    private Cidade cidade;
    private boolean cliente;
    private boolean fornecedor;
    private boolean funcionario;
    private String nome;
    private String endereco;
    private String telefone;
    private Date dataCadastro;
    private String email;
    private Status status;

    public int getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(int codPessoa) {
        if (codPessoa < 0) {
            throw new InvalidParameterException("Código inválido.");
        }
        this.codPessoa = codPessoa;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
        this.pessoaJuridica = null;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaFisica = null;
        this.pessoaJuridica = pessoaJuridica;
    }

    public String getDocumento() {
        if (pessoaFisica != null) {
            return pessoaFisica.getCpf();
        } else if (pessoaJuridica != null) {
            return pessoaJuridica.getCnpj();
        } else {
            return "";
        }
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        if (cidade == null) {
            throw new InvalidParameterException("Cidade inválida.");
        }
        this.cidade = cidade;
    }

    public boolean isCliente() {
        return cliente;
    }

    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }

    public boolean isFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(boolean fornecedor) {
        this.fornecedor = fornecedor;
    }

    public boolean isFuncionario() {
        return funcionario;
    }

    public void setFuncionario(boolean funcionario) {
        this.funcionario = funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.trim().length() < 3 || nome.trim().length() > 200) {
            throw new InvalidParameterException("Nome inválido.");
        }
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        //TODO Validar telefone
        this.telefone = telefone;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        //TODO Validar e-mail...
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pessoa) {
            return ((Pessoa) obj).getCodPessoa() == getCodPessoa();
        }
        return false;
    }

    @Override
    public String toString() {
        return getNome();
    }

}
