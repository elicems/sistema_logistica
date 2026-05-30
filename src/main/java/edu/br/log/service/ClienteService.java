package edu.br.log.service;

import edu.br.log.dao.ClienteDAO;
import edu.br.log.model.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteService {
    private final ClienteDAO dao = new ClienteDAO();

    public int criar(Cliente cliente)throws SQLException{
        validar(cliente);
        return dao.cadastraCliente(cliente);
    }
    public boolean atualizarCliente(Cliente cliente)throws SQLException{
        if(cliente.getId() == null){
            throw new IllegalArgumentException("Informe o ID do cliente");
        }
        validar(cliente);
        return dao.atualizarCliente(cliente);
    }
    public boolean remover(int id)throws SQLException{
        if(id<=0){
            throw new IllegalArgumentException("ID inválido");
        }
        return dao.removerPorId(id);
    }
    public Cliente buscar(int id)throws SQLException{
        if(id<=0){
            throw new IllegalArgumentException("ID inválido");
        }
        return dao.buscarPorId(id);
    }

    public List<Cliente> listar()throws SQLException{
        return dao.listarClientes();
    }

    public List<Cliente> buscarPorNome(String trecho)throws SQLException{
        if(trecho==null)trecho="";
        return dao.buscaPorNome(trecho.trim());
    }
    private void validar(Cliente cliente){
        if(cliente.getNomeUsuario() == null || cliente.getNomeUsuario().trim().isEmpty()){
            throw new IllegalArgumentException("Nome é obrigátorio");
        }
        if (cliente.getEmailUsuario() == null || cliente.getEmailUsuario().trim().isEmpty()){
            throw new IllegalArgumentException("Email é obrigátorio");
        }
    }

}
