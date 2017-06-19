package com.jdenner.model.dao;

import com.jdenner.model.PessoaFisica;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Juliano
 */
public class PessoaFisicaDAO {

    public static int salvar(PessoaFisica pf, Conexao con) throws Exception {
        if (existe(pf)) {
            throw new Exception("CPF já cadastrado.");
        }
        if (pf.getCodPessoaFisica() == 0) {
            return inserir(pf, con);
        } else {
            return alterar(pf, con);
        }
    }

    private static boolean existe(PessoaFisica pf) throws Exception {

        String sql = "select count(codpessoafisica) from tbpessoafisica where cpf=? and codpessoafisica<>?";
        Conexao c = new Conexao();
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setString(1, pf.getCpf());
        ps.setInt(2, pf.getCodPessoaFisica());

        ResultSet rs = ps.executeQuery();
        rs.next();
        return (rs.getInt(1) > 0);
    }

    private static int inserir(PessoaFisica pf, Conexao con) throws Exception {

        String sql = "insert into tbpessoafisica (cpf,rg,datanascimento) values (?,?,?)";
        PreparedStatement ps = con.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, pf.getCpf());
        ps.setString(2, pf.getRg());
        ps.setDate(3, new Date(pf.getDataNascimento().getTime()));
        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    private static int alterar(PessoaFisica pf, Conexao con) throws Exception {

        String sql = "update tbpessoafisica set cpf=?,rg=?,datanascimento=? where codpessoafisica=?";
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setString(1, pf.getCpf());
        ps.setString(2, pf.getRg());
        ps.setDate(3, new Date(pf.getDataNascimento().getTime()));
        ps.setInt(4, pf.getCodPessoaFisica());
        ps.execute();

        return pf.getCodPessoaFisica();
    }

    public static int excluir(int codPessoaFisica, Conexao con) throws Exception {

        String sql = "delete from tbpessoafisica where codpessoafisica=?";
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setInt(1, codPessoaFisica);
        ps.execute();

        return 0;
    }

    public static PessoaFisica get(int codPessoaFisica) throws Exception {

        String sql = "select * from tbpessoafisica where codpessoafisica=? ";
        Conexao con = new Conexao();
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setInt(1, codPessoaFisica);
        ResultSet rs = ps.executeQuery();
        PessoaFisica pf = null;
        if (rs.next()) {
            pf = new PessoaFisica();
            pf.setCodPessoaFisica(rs.getInt("codpessoafisica"));
            pf.setCpf(rs.getString("cpf"));
            pf.setRg(rs.getString("rg"));
            pf.setDataNascimento(rs.getDate("dataNascimento"));
        }

        return pf;
    }
}
