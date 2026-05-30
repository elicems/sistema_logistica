package edu.br.log.controller;

import edu.br.log.model.Pedido;
import edu.br.log.service.PedidoService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoController {
    private final PedidoService service = new PedidoService();

    public int CriarPedido(String descricao, Double valor, LocalDateTime dataPedido,Integer qtdProdutosPedido) throws SQLException {
        Pedido p = new Pedido(descricao,valor,dataPedido,qtdProdutosPedido);
        return service.criarPedido(p);
    }
    public Pedido buscarPedido(Integer id)throws SQLException{
        return service.buscar(id);
    }
    public List<Pedido> listar()throws SQLException{
        return service.listarPedidos();
    }
}
