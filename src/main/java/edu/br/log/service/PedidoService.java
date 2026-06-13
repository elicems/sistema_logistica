package edu.br.log.service;

import edu.br.log.dao.EstoqueDAO;
import edu.br.log.dao.PedidoDAO;
import edu.br.log.model.ItemEstoque;
import edu.br.log.model.ItemPedido;
import edu.br.log.model.Pedido;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PedidoService {
    private final PedidoDAO dao = new PedidoDAO();
    private final EstoqueDAO edao = new EstoqueDAO();

    public int criarPedido(Pedido pedido) throws SQLException {
        validar(pedido);
        return dao.CriarPedido(pedido);
    }
    public List<ItemPedido> carrinho()throws SQLException{
        List<ItemPedido> lista = new ArrayList<>();
        if(!listarPedidos().isEmpty()){
            for(ItemPedido p : lista){
               carrinho().add(p);
            }
        }else {
            throw new IllegalArgumentException("Lista de pedidos vazia");
        }
        return lista;
    }
    public Pedido buscar(int id)throws SQLException{
        if(id<=0){
            throw new IllegalArgumentException("ID inválido");
        }
        return dao.buscarPorId(id);
    }
    public List<Pedido> listarPedidos()throws SQLException{
        return dao.listarTodosPedidos();
    }
    private void validar(Pedido pedido){
        if(pedido.getDescricao() == null||pedido.getDescricao().trim().isEmpty()){
            throw new IllegalArgumentException("Descrição é obrigátorio");
        }
    }
}
