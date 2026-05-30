package edu.br.log.service;

import edu.br.log.dao.PedidoDAO;
import edu.br.log.model.Pedido;

import java.sql.SQLException;
import java.util.List;

public class PedidoService {
    private final PedidoDAO dao = new PedidoDAO();

    public int criarPedido(Pedido pedido)throws SQLException{
        validar(pedido);
        return dao.InserirPedido(pedido);
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
    public Pedido etiquetaPedido(Pedido pedido){

    }
    private void validar(Pedido pedido){
        if(pedido.getDescricao() == null||pedido.getDescricao().trim().isEmpty()){
            throw new IllegalArgumentException("Descrição é obrigátorio");
        }
    }
}
