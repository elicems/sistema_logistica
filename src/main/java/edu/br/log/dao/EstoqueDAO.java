package edu.br.log.dao;

import edu.br.log.model.ItemEstoque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
    public int inserirItem(ItemEstoque item) throws SQLException{
        String sql = "insert into produtos (nome_produto,sku,ean,quantidade,preco_venda) values (?,?,?,?,?)";
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, item.getNomeProduto());
            ps.setString(2,item.getSku());
            ps.setString(3,item.getEan());
            ps.setInt(4,item.getQuantidade());
            ps.setDouble(5,item.getPrecoVenda());

            int linhas = ps.executeUpdate();
            if(linhas == 0){
                throw new SQLException("Inserção falhou: nenhuma linha afetada");
            }
            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    return rs.getInt(1);
                }
            }
            throw new SQLException("Inserção falhou: não foi possível obter o ID gerado.");
        }
    }
    public boolean atualizarItem(ItemEstoque item) throws SQLException{
        String sql = "update produtos set nome_produto=?,sku=?,ean=?,quantidade=?,preco_venda=? where id=?";
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, item.getNomeProduto());
            ps.setString(2, item.getSku());
            ps.setString(3,item.getEan());
            ps.setInt(4,item.getQuantidade());
            ps.setDouble(5,item.getPrecoVenda());
            ps.setInt(6,item.getId());

            return ps.executeUpdate() > 0;
        }
    }
    public boolean removerPorID(int id)throws SQLException{
        String sql = "delete from produtos where id=?";
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1,id);
                return ps.executeUpdate() > 0;
        }
    }
    public ItemEstoque bucarPorID(int id)throws SQLException{
        String sql = "select id,nome_produto,sku,ean,quantidade,preco_venda from produtos where id=?";
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return mapItemEstoque(rs);
                }
                return null;
            }
        }
    }
    public List<ItemEstoque> listarTodos()throws SQLException{
        String sql = "select id,nome_produto,sku,ean,quantidade,preco_venda from produtos order by id";
        List<ItemEstoque> lista = new ArrayList<>();

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                lista.add(mapItemEstoque(rs));
            }
        }
        return lista;
    }
    public List<ItemEstoque> buscarPorNome(String trecho)throws SQLException{
        String sql = "select id,nome_produto,sku,ean,quantidade,preco_venda from produtos where nome_produto like ? order by nome";
        List<ItemEstoque> lista = new ArrayList<>();

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1,"%" + trecho + "%");
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    lista.add(mapItemEstoque(rs));
                }
            }
        }
        return lista;
    }
    private ItemEstoque mapItemEstoque(ResultSet rs)throws SQLException{
        return new ItemEstoque(
                rs.getInt("id"),
                rs.getString("nome_produto"),
                rs.getString("sku"),
                rs.getString("ean"),
                rs.getInt("quantidade"),
                rs.getDouble("preco_venda")
        );
    }
}
