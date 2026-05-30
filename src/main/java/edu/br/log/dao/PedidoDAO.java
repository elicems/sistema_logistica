package edu.br.log.dao;

import edu.br.log.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Adicionar horario do pedido!!!
public class PedidoDAO {
    public int InserirPedido(Pedido pedido) throws SQLException {
        String sql = "insert into pedidos(descricao,valor)values(?,?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, pedido.getDescricao());
            ps.setDouble(2, pedido.getValorTotal());

            int linhas = ps.executeUpdate();
            if (linhas == 0) {
                throw new SQLException("Inserção falhou! Nenhuma linha afetada");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pedido.setIdPedido(rs.getInt(1));
                }
            }
        }
        throw new SQLException("Inserção falhou: não foi possível obter o ID gerado");
    }

    public Pedido buscarPorId(int id) throws SQLException {
        String sql = "select descricao,valor from pedidos where id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapPedidos(rs);
                }
            }
        }
        return null;
    }

    public List<Pedido> listarTodosPedidos() throws SQLException {
        String sql = "select descricao,valor from pedidos order by id";
        List<Pedido> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                lista.add(mapPedidos(rs));

            }

        }
        return lista;
    }

    private Pedido mapPedidos(ResultSet rs) throws SQLException {
        return new Pedido(
                rs.getString("descricao"),
                rs.getDouble("valor")
        );
    }
}
