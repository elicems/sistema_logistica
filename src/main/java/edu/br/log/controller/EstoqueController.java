package edu.br.log.controller;

import edu.br.log.model.ItemEstoque;
import edu.br.log.service.EstoqueService;

import java.sql.SQLException;
import java.util.List;

public class EstoqueController {
    private final EstoqueService service = new EstoqueService();

    public int criarItemEstoque(String nomeProduto,String sku,String ean,Integer quantidade,Double precoVenda)throws SQLException {
        ItemEstoque i = new ItemEstoque(nomeProduto,sku,ean,quantidade,precoVenda);
        return service.criar(i);
    }
    public boolean atualizarItem(Integer id,String nomeProduto,String sku,String ean,Integer quantidade,Double precoVenda)throws SQLException{
        ItemEstoque i = new ItemEstoque(id,nomeProduto,sku,ean,quantidade,precoVenda);
        return service.atualizar(i);
    }
    public boolean atualizarEstoque(int id,int qtd)throws SQLException{
        return service.atualizarEstoque(id,qtd);
    }

    public boolean remover(Integer id)throws SQLException{
        return service.remover(id);
    }
    public ItemEstoque buscarItem(Integer id)throws SQLException{
        return service.buscar(id);
    }
    public List<ItemEstoque> listarItens()throws SQLException{
        return service.listar();
    }
    public List<ItemEstoque> buscarPorNome(String trecho)throws SQLException{
        return service.buscarPorNome(trecho);
    }
}
