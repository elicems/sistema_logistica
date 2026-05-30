package edu.br.log.dao;

import edu.br.log.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public int cadastraCliente(Cliente cliente) throws SQLException {
        String sql = "insert into cliente(nome_usuario,email_usuario) values(?,?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, cliente.getNomeUsuario());
                ps.setString(2, cliente.getEmailUsuario());

            int linhas = ps.executeUpdate();
            if (linhas == 0) {
                throw new SQLException("Inserção falhou: nenhuma linha afetada");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("Inserção falhou! Não foi possível obter o id gerado");
    }

    public boolean atualizarCliente(Cliente cliente) throws SQLException {
        String sql = "update cliente set (nome_usuario=?,email_usuario=?) values(?,?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getNomeUsuario());
            ps.setString(2, cliente.getEmailUsuario());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean removerPorId(int id) throws SQLException {
        String sql = "delete from cliente where id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public Cliente buscarPorId(int id) throws SQLException {
        String sql = "select nome_usuario,email_usuario from cliente where id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapCliente(rs);
                }
            }
        }

        return null;
    }
    public List<Cliente> listarClientes()throws SQLException{
        String sql = "select nome_usuario,email_usuario from cliente order by id";
        List<Cliente> lista = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                lista.add(mapCliente(rs));
            }
        }
        return lista;
    }
    public List<Cliente> buscaPorNome(String trecho)throws SQLException{
        String sql = "select nome_usuario,email_usuario from cliente where id=?";
        List<Cliente> lista = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,"%" + trecho + "%");
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    lista.add(mapCliente(rs));
                }
            }
        }
        return lista;
    }
    private Cliente mapCliente(ResultSet rs)throws SQLException{
        return new Cliente(
                rs.getInt("id"),
                rs.getString("nome_usuario"),
                rs.getString("email_usuario")
        );
    }


}
