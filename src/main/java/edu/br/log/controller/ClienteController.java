package edu.br.log.controller;

import edu.br.log.model.Cliente;
import edu.br.log.service.ClienteService;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {
    private final ClienteService service = new ClienteService();

    public int CadastrarCliente(String nome,String email)throws SQLException{
        Cliente c = new Cliente(nome,email);
        return service.criar(c);
    }
    public boolean atualizarCliente(String nome,String email)throws SQLException{
        Cliente c = new Cliente(nome,email);
        return service.atualizarCliente(c);
    }
    public boolean remover(Integer id)throws SQLException{
        return service.remover(id);
    }
    public Cliente buscarCliente(Integer id)throws SQLException{
        return service.buscar(id);
    }
    public List<Cliente> listar()throws SQLException{
        return service.listar();
    }
    public List<Cliente> buscarPorNome(String trecho)throws SQLException{
        return service.buscarPorNome(trecho);
    }
}
