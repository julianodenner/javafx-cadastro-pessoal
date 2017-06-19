package com.jdenner.model.dao;

import com.jdenner.model.PessoaJuridica;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Juliano
 */
public class PessoaJuridicaDAO {

    public static int salvar(PessoaJuridica pj, Conexao con) throws Exception {

        if (existe(pj)) {
            throw new Exception("CNPJ já cadastrado.");
        }

        if (pj.getCodPessoaJuridica() == 0) {
            return inserir(pj, con);
        } else {
            return alterar(pj, con);
        }
    }

    private static boolean existe(PessoaJuridica pj) throws Exception {

        String sql = "select count(codpessoajuridica) from tbpessoajuridica where cnpj=? and codpessoajuridica<>?";
        Conexao c = new Conexao();
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setString(1, pj.getCnpj());
        ps.setInt(2, pj.getCodPessoaJuridica());

        ResultSet rs = ps.executeQuery();
        rs.next();
        return (rs.getInt(1) > 0);
    }

    private static int inserir(PessoaJuridica pj, Conexao con) throws Exception {

        String sql = "insert into tbpessoajuridica (cnpj,ie,razaosocial) values (?,?,?)";
        PreparedStatement ps = con.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, pj.getCnpj());
        ps.setString(2, pj.getIe());
        ps.setString(3, pj.getRazaoSocial());
        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    private static int alterar(PessoaJuridica pj, Conexao con) throws Exception {

        String sql = "update tbpessoajuridica set cnpj=?,ie=?,razaosocial=? where codpessoajuridica=?";
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setString(1, pj.getCnpj());
        ps.setString(2, pj.getIe());
        ps.setString(3, pj.getRazaoSocial());
        ps.setInt(4, pj.getCodPessoaJuridica());
        ps.execute();

        return pj.getCodPessoaJuridica();
    }

    public static int excluir(int codPessoaJuridica, Conexao con) throws Exception {

        String sql = "delete from tbpessoajuridica where codpessoajuridica=?";
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setInt(1, codPessoaJuridica);
        ps.execute();

        return 0;
    }

    public static PessoaJuridica get(int codPessoaJuridica) throws Exception {

        String sql = "select * from tbpessoajuridica where codpessoajuridica=? ";
        Conexao con = new Conexao();
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setInt(1, codPessoaJuridica);
        ResultSet rs = ps.executeQuery();
        PessoaJuridica pj = null;
        if (rs.next()) {
            pj = new PessoaJuridica();
            pj.setCodPessoaJuridica(rs.getInt("codpessoajuridica"));
            pj.setCnpj(rs.getString("cnpj"));
            pj.setIe(rs.getString("ie"));
            pj.setRazaoSocial(rs.getString("razaosocial"));
        }

        return pj;
    }
}
