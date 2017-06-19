package com.jdenner.model.dao;

import com.jdenner.model.Cidade;
import com.jdenner.model.Estado;
import com.jdenner.model.Status;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Bruna
 */
public class CidadeDAO {

    public static Cidade get(int codCidade) throws Exception {

        String sql = "select * from tbcidade where codcidade=? ";
        Conexao con = new Conexao();
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setInt(1, codCidade);
        ResultSet rs = ps.executeQuery();
        Cidade cidade = null;
        if (rs.next()) {
            cidade = new Cidade();
            cidade.setCodCidade(rs.getInt("codcidade"));
            cidade.setNome(rs.getString("nome"));
            cidade.setEstado(EstadoDAO.get(rs.getInt("codestado")));
            cidade.setStatus(Status.get(rs.getString("status")));
        }
        return cidade;
    }

    public static ObservableList<Cidade> listar(boolean somenteAtivos, Estado estado) throws Exception {

        String sql = "select * from tbcidade ";
        sql += " where codestado=?";
        sql += ((somenteAtivos) ? " and status='A' " : " ");
        sql += " order by nome";
        Conexao con = new Conexao();
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setInt(1, estado.getCodEstado());
        ResultSet rs = ps.executeQuery();
        ObservableList<Cidade> lista = FXCollections.observableArrayList();
        while (rs.next()) {
            Cidade cidade = new Cidade();
            cidade.setCodCidade(rs.getInt("codcidade"));
            cidade.setNome(rs.getString("nome"));
            cidade.setEstado(EstadoDAO.get(rs.getInt("codestado")));
            cidade.setStatus(rs.getString("status"));
            lista.add(cidade);

        }
        return lista;
    }
}
