package com.jdenner.model.dao;

import com.jdenner.model.Cidade;
import com.jdenner.model.Pessoa;
import com.jdenner.model.Status;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Juliano
 */
public class PessoaDAO {

    public static int salvar(Pessoa p, Conexao con) throws Exception {
        if (p.getCodPessoa() == 0) {
            return inserir(p, con);
        } else {
            return alterar(p, con);
        }
    }

    private static int inserir(Pessoa p, Conexao con) throws Exception {

        String sql = "insert into tbpessoa (" + (p.getPessoaFisica() != null ? "codpessoafisica" : "codpessoajuridica") + ",codcidade,nome,endereco,telefone,datacadastro,email,status) values (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        if (p.getPessoaFisica() != null) {
            ps.setInt(1, p.getPessoaFisica().getCodPessoaFisica());
        } else {
            ps.setInt(1, p.getPessoaJuridica().getCodPessoaJuridica());
        }
        ps.setInt(2, p.getCidade().getCodCidade());
        ps.setString(3, p.getNome());
        ps.setString(4, p.getEndereco());
        ps.setString(5, p.getTelefone());
        ps.setDate(6, new Date(p.getDataCadastro().getTime()));
        ps.setString(7, p.getEmail());
        ps.setString(8, p.getStatus().getCodStatus());
        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    private static int alterar(Pessoa p, Conexao con) throws Exception {

        String sql = "update tbpessoa set codpessoafisica=?,codpessoajuridica=?,codcidade=?,nome=?,endereco=?,telefone=?,datacadastro=?,email=?,status=? where codpessoa=?";
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        if (p.getPessoaFisica() != null) {
            ps.setInt(1, p.getPessoaFisica().getCodPessoaFisica());
            ps.setNull(2, Types.INTEGER);
        } else {
            ps.setNull(1, Types.INTEGER);
            ps.setInt(2, p.getPessoaJuridica().getCodPessoaJuridica());
        }
        ps.setInt(3, p.getCidade().getCodCidade());
        ps.setString(4, p.getNome());
        ps.setString(5, p.getEndereco());
        ps.setString(6, p.getTelefone());
        ps.setDate(7, new Date(p.getDataCadastro().getTime()));
        ps.setString(8, p.getEmail());
        ps.setString(9, p.getStatus().getCodStatus());
        ps.setInt(10, p.getCodPessoa());
        ps.execute();

        return p.getCodPessoa();
    }

    public static ObservableList<Pessoa> listar(boolean somenteAtivos, boolean clientes, boolean fornecedores, boolean funcionarios, String filtro) throws Exception {

        String sql = "select p.*, c.codpessoa as cliente, fo.codpessoa as fornecedor, fu.codpessoa as funcionario ";
        sql += "from tbpessoa as p ";
        sql += (clientes ? "inner" : "left") + " join tbcliente as c on c.codpessoa = p.codpessoa ";
        sql += (fornecedores ? "inner" : "left") + " join tbfornecedor as fo on fo.codpessoa = p.codpessoa ";
        sql += (funcionarios ? "inner" : "left") + " join tbfuncionario as fu on fu.codpessoa = p.codpessoa ";
        sql += " where 1=1 ";
        sql += (somenteAtivos) ? " and p.status = 'A' " : " ";
        sql += (!filtro.trim().isEmpty()) ? " and p.nome like '%" + filtro + "%' " : " ";
        sql += "order by p.nome";
        Conexao con = new Conexao();
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Pessoa> lista = FXCollections.observableArrayList();

        while (rs.next()) {
            Pessoa p = new Pessoa();
            p.setCodPessoa(rs.getInt("codpessoa"));
            if (rs.getInt("codpessoafisica") != 0) {
                p.setPessoaFisica(PessoaFisicaDAO.get(rs.getInt("codpessoafisica")));
            } else {
                p.setPessoaJuridica(PessoaJuridicaDAO.get(rs.getInt("codpessoajuridica")));
            }
            p.setCidade(CidadeDAO.get(rs.getInt("codcidade")));
            p.setCliente(rs.getInt("cliente") != 0);
            p.setFornecedor(rs.getInt("fornecedor") != 0);
            p.setFuncionario(rs.getInt("funcionario") != 0);
            p.setNome(rs.getString("nome"));
            p.setEndereco(rs.getString("endereco"));
            p.setEmail(rs.getString("email"));
            p.setTelefone(rs.getString("telefone"));
            p.setDataCadastro(rs.getDate("datacadastro"));
            p.setStatus(Status.get(rs.getString("status")));
            lista.add(p);
        }
        return lista;
    }
}
