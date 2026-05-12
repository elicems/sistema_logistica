package edu.br.log.service;

import edu.br.log.dao.EstoqueDAO;
import edu.br.log.model.ItemEstoque;

import java.sql.SQLException;
import java.util.List;

public class EstoqueService {
    private final EstoqueDAO dao = new EstoqueDAO();

    public int criar(ItemEstoque item)throws SQLException{
        validar(item);
        return dao.inserirItem(item);
    }
    public boolean atualizar(ItemEstoque item)throws SQLException{
        if(item.getId() == null){
            throw new IllegalArgumentException("Produto sem ID não pode ser atualizado");
        }
        validar(item);
        return dao.atualizarItem(item);
    }
    public boolean remover(int id)throws SQLException{
        if(id<= 0){
            throw new IllegalArgumentException("ID inválido");
        }
        return dao.removerPorID(id);
    }
    public ItemEstoque buscar(int id)throws SQLException{
        if(id<=0){
            throw new IllegalArgumentException("ID inválido");
        }
        return dao.bucarPorID(id);
    }
    public List<ItemEstoque> listar()throws SQLException{
        return dao.listarTodos();
    }
    public List<ItemEstoque> buscarPorNome(String trecho)throws SQLException{
        if(trecho==null)trecho = "";
        return dao.buscarPorNome(trecho.trim());
    }
    private void validar(ItemEstoque item){
        if(item.getNomeProduto() == null || item.getNomeProduto().trim().isEmpty()){
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if(item.getEan() == null ||item.getEan().trim().isEmpty()){
            throw new IllegalArgumentException("EAN é obrigatório");
        }
        if(item.getSku() == null||item.getSku().trim().isEmpty()){
            throw new IllegalArgumentException("SKU é obrigatório");
        }
    }
}
