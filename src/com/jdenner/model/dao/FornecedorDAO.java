package com.jdenner.model.dao;

import com.jdenner.model.Fornecedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Juliano
 */
public class FornecedorDAO {

    public static void salvar(Fornecedor f, Conexao con) throws Exception {
        if (!existe(f)) {
            inserir(f, con);
        }
    }

    private static boolean existe(Fornecedor c) throws Exception {

        String sql = "select count(codpessoa) from tbfornecedor where codpessoa=?";
        Conexao con = new Conexao();
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setInt(1, c.getCodPessoa());

        ResultSet rs = ps.executeQuery();
        rs.next();
        return (rs.getInt(1) > 0);
    }

    private static void inserir(Fornecedor f, Conexao con) throws Exception {
        String sql = "insert into tbfornecedor (codpessoa) values (?)";
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setInt(1, f.getCodPessoa());
        ps.execute();
    }

    public static void excluir(int codPessoa, Conexao con) throws Exception {
        String sql = "delete from tbfornecedor where codpessoa=?";
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setInt(1, codPessoa);
        ps.execute();
    }
}
