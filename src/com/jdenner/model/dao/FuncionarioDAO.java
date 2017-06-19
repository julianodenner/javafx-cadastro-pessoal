package com.jdenner.model.dao;

import com.jdenner.model.Funcionario;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 *
 * @author Juliano
 */
public class FuncionarioDAO {

    public static void salvar(Funcionario f, Conexao con) throws Exception {
        if (!existe(f)) {
            inserir(f, con);
        } else {
            alterar(f, con);
        }
    }

    private static boolean existe(Funcionario f) throws Exception {

        String sql = "select count(codpessoa) from tbfuncionario where codpessoa=?";
        Conexao con = new Conexao();
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setInt(1, f.getCodPessoa());

        ResultSet rs = ps.executeQuery();
        rs.next();
        return (rs.getInt(1) > 0);
    }

    private static void inserir(Funcionario f, Conexao con) throws Exception {
        String sql = "insert into tbfuncionario (codpessoa, dataadmissao, datademissao) values (?,?,?)";
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setInt(1, f.getCodPessoa());
        ps.setDate(2, new Date(f.getDataAdmissao().getTime()));
        if (f.getDataDemissao() != null) {
            ps.setDate(3, new Date(f.getDataDemissao().getTime()));
        } else {
            ps.setNull(3, Types.DATE);
        }
        ps.execute();
    }

    private static void alterar(Funcionario f, Conexao con) throws Exception {
        String sql = "update tbfuncionario set dataadmissao=?, datademissao=? where codpessoa=?";
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setDate(1, new Date(f.getDataAdmissao().getTime()));
        if (f.getDataDemissao() != null) {
            ps.setDate(2, new Date(f.getDataAdmissao().getTime()));
        } else {
            ps.setNull(2, Types.DATE);
        }
        ps.setInt(3, f.getCodPessoa());
        ps.execute();
    }

    public static void excluir(int codPessoa, Conexao con) throws Exception {
        String sql = "delete from tbfuncionario where codpessoa=?";
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setInt(1, codPessoa);
        ps.execute();
    }

    public static Funcionario get(int codPessoa) throws Exception {

        String sql = "select * from tbfuncionario where codpessoa=? ";
        Conexao con = new Conexao();
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setInt(1, codPessoa);
        ResultSet rs = ps.executeQuery();
        Funcionario f = null;
        if (rs.next()) {
            f = new Funcionario();
            f.setCodPessoa(rs.getInt("codpessoa"));
            f.setDataAdmissao(rs.getDate("dataadmissao"));
            f.setDataDemissao(rs.getDate("datademissao"));
        }
        return f;
    }
}
