package com.jdenner.model.dao;

import com.jdenner.model.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Juliano
 */
public class ClienteDAO {

    public static void salvar(Cliente c, Conexao con) throws Exception {
        if (!existe(c)) {
            inserir(c, con);
        }
    }

    private static boolean existe(Cliente c) throws Exception {

        String sql = "select count(codpessoa) from tbcliente where codpessoa=?";
        Conexao con = new Conexao();
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setInt(1, c.getCodPessoa());

        ResultSet rs = ps.executeQuery();
        rs.next();
        return (rs.getInt(1) > 0);
    }

    private static void inserir(Cliente p, Conexao con) throws Exception {
        String sql = "insert into tbcliente (codpessoa) values (?)";
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setInt(1, p.getCodPessoa());
        ps.execute();
    }

    public static void excluir(int codPessoa, Conexao con) throws Exception {
        String sql = "delete from tbcliente where codpessoa=?";
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setInt(1, codPessoa);
        ps.execute();
    }
}
