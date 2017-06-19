package com.jdenner.model.dao;

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
public class EstadoDAO {

    public static ObservableList<Estado> listar(boolean somenteAtivos) throws Exception {

        String sql = "select * from tbestado ";
        sql += ((somenteAtivos) ? " where status='A' " : " ");
        sql += " order by nome";
        Conexao con = new Conexao();
        PreparedStatement ps = con.getConexao().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        ObservableList<Estado> lista = FXCollections.observableArrayList();
        while (rs.next()) {
            Estado estado = new Estado();
            estado.setCodEstado(rs.getInt("codestado"));
            estado.setNome(rs.getString("nome"));
            estado.setSigla(rs.getString("sigla"));
            estado.setStatus(rs.getString("status"));
            lista.add(estado);

        }
        return lista;
    }

    public static Estado get(int codEstado) throws Exception {

        String sql = "select * from tbestado where codestado=? ";
        Conexao con = new Conexao();
        PreparedStatement ps = con.getConexao().prepareStatement(sql);
        ps.setInt(1, codEstado);
        ResultSet rs = ps.executeQuery();
        Estado estado = null;
        if (rs.next()) {
            estado = new Estado();
            estado.setCodEstado(rs.getInt("codestado"));
            estado.setNome(rs.getString("nome"));
            estado.setSigla(rs.getString("sigla"));
            estado.setStatus(rs.getString("status"));
        }
        return estado;
    }
}
