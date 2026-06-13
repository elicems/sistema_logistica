package edu.br.log.view;

import edu.br.log.controller.ClienteController;
import edu.br.log.controller.EstoqueController;
import edu.br.log.controller.PedidoController;
import edu.br.log.model.Cliente;
import edu.br.log.model.ItemEstoque;
import edu.br.log.model.Pedido;

import java.sql.SQLException;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private final EstoqueController estoqueController = new EstoqueController();
    private final ClienteController clienteController = new ClienteController();
    private final PedidoController pedidoController = new PedidoController();
    private final Scanner sc = new Scanner(System.in);

    public void inicar(){
        int op;
        do{
            menu();
            op = lerInt("Escolha: ");

            try {
                switch (op){
                    case 1 -> inicarProdutos();
                    case 2 -> iniciarCliente();
                    case 3 -> iniciarPedido();
                    case 0 -> System.out.println("Encerrando...");
                    default -> System.out.println("Opção inválida");
                }
            }catch (IllegalArgumentException e){
                System.out.println("Erro de validação: " + e.getMessage());
            }catch (Exception e){
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }while (op != 0);
    }
    private void inicarProdutos(){
        int op;
        do{
            menuProdutos();
            op = lerInt("Escolha: ");

            try{
                switch (op){
                    case 1 -> criarProduto();
                    case 2 -> listarProdutos();
                    case 3 -> buscarProdutoPorId();
                    case 4 -> buscarProdutoPorNome();
                    case 5 -> atualizarProduto();
                    case 6 -> removerProduto();
                    case 0 -> System.out.println("Voltando para o menu...");
                    default -> System.out.println("Opção inválida");
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }while (op != 0);
    }
    private void iniciarCliente(){
        int op;
        do{
            menuCliente();
            op = lerInt("Escolha: ");

            try{
                switch (op){
                    case 1 -> cadastrarCliente();
                    case 2 -> listarClientes();
                    case 3 -> buscarClientePorId();
                    case 4 -> buscarClientePorNome();
                    case 5 -> atualizarCadastroCliente();
                    case 6 -> removerCadastroCliente();
                    case 7 -> menu();
                    case 0 -> System.out.println("Encerrando...");
                    default -> System.out.println("Opção inválida");
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }while (op != 0);
    }
    private void iniciarPedido(){
        int op;
        do {
            menuPedidos();
            op = lerInt("Escolha: ");

            try {
                switch (op){
                    case 1 -> criarPedido();
                    case 2 -> listarPedidos();
                    case 3 -> buscarPedidoPorId();
                    case 4 -> {
                        int id = lerInt("Informe o id do pedido: ");
                        for(Pedido p:pedidoController.listar()){
                            if(p.getIdPedido().equals(id)){
                                imprimirEtiqueta(p);
                            }else {
                                System.out.println("ID não encontrado");
                            }
                        }
                    }
                    case 5 -> menu();
                    case 0 -> System.out.println("Encerrando...");
                    default -> System.out.println("Opção inválida");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }while(op!=0);
    }
    private void menu(){
        System.out.println("\n===Menu Principal");
        System.out.println("1) Acessar menu de produtos");
        System.out.println("2) Acessar menu de clientes");
        System.out.println("3) Acessar menu de pedidos");
        System.out.println("0) Sair");
    }
    private void menuProdutos() {
        System.out.println("\n=== CRUD Cadastro de produtos ===");
        System.out.println("1) Criar produto");
        System.out.println("2) Listar produtos");
        System.out.println("3) Buscar produto por ID");
        System.out.println("4) Buscar produto por nome (trecho)");
        System.out.println("5) Atualizar produto");
        System.out.println("6) Remover produto");
        System.out.println("0) Voltar para o menu principal");
    }
    private void menuCliente(){
        System.out.println("\n=== CRUD Cadastro de clientes ===");
        System.out.println("1) Cadastrar cliente");
        System.out.println("2) Listar clientes");
        System.out.println("3) Buscar cliente por ID");
        System.out.println("4) Buscar cliente por nome (trecho)");
        System.out.println("5) Atualizar cadastro cliente");
        System.out.println("6) Remover cadastro cliente");
        System.out.println("0) Voltar para o menu principal");
    }
    private void menuPedidos(){
        System.out.println("\n=== CRUD de pedidos ===");
        System.out.println("1) Criar pedido");
        System.out.println("2) Listar todos os pedidos");
        System.out.println("3) Buscar pedido por ID");
        System.out.println("4) Imprimir etiqueta do pedido");
    }

    private void criarProduto()throws SQLException{
        System.out.println("\n---Adicionar item ao estoque---");
        String nomeProduto = lerLinha("Nome do produto: ");
        String sku = lerLinha("SKU do produto: ");
        String ean = lerLinha("EAN do produto: ");
        Integer quantidade = lerInt("Quantidade: ");
        Double precoVenda = lerDouble("Preço Venda: ");

        int id = estoqueController.criarItemEstoque(nomeProduto,sku,ean,quantidade,precoVenda);
        System.out.println("Item adicionado com id: " + id);
    }
    private void listarProdutos()throws SQLException{
        System.out.println("\n---Listar itens---");
        List<ItemEstoque> itens = estoqueController.listarItens();
        if(itens.isEmpty()){
            System.out.println("Nenhum item encontrado");
            return;
        }
        for (ItemEstoque i:itens){
            System.out.println(i.toString());
        }
    }
    private void buscarProdutoPorId()throws SQLException{
        System.out.println("\n---Buscar por id---");
        int id = lerInt("Id: ");
        ItemEstoque i = estoqueController.buscarItem(id);
        System.out.println(i == null ? "Não encontrado. " : i.toString());
    }
    private void buscarProdutoPorNome()throws SQLException{
        System.out.println("\n---Buscar por nome---");
        String trecho = lerLinha("Trecho do nome: ");
        List<ItemEstoque> itens = estoqueController.buscarPorNome(trecho);
        if(itens.isEmpty()){
            System.out.println("Nenhum item encontrado.");
            return;
        }
        for(ItemEstoque i:itens){
            System.out.println(i);
        }
    }
    private void atualizarProduto()throws SQLException{
        System.out.println("\n---Atualizar item---");
        int id = lerInt("ID: ");
        String nomeProduto = lerLinha("Novo nome: ");
        String sku = lerLinha("Novo SKU: ");
        String ean = lerLinha("Novo EAN: ");
        Integer quantidade = lerInt("Nova quantidade: ");
        Double precoVenda = lerDouble("Novo preco: ");

        boolean ok = estoqueController.atualizarItem(id,nomeProduto,sku,ean,quantidade,precoVenda);
        System.out.println(ok?"Atualizado com sucesso":"Id não encontrado.");
    }
    private void removerProduto()throws SQLException{
        System.out.println("\n---Remover item---");
        int id = lerInt("ID: ");
        boolean ok = estoqueController.remover(id);
        System.out.println(ok?"Removido com sucesso.":"ID não encontrado.");
    }
    private void cadastrarCliente()throws SQLException{
        System.out.println("\n---Cadastro de cliente---");
        String nomeCliente = lerLinha("Nome cliente: ");
        String emailCliente = lerLinha("Email cliente: ");

        int id = clienteController.CadastrarCliente(nomeCliente,emailCliente);
        System.out.println("Cliente cadastrado com id: " + id);
    }
    private void listarClientes()throws SQLException{
        System.out.println("\n---Listar Clientes---");
        List<Cliente> clientes = clienteController.listar();
        if(clientes.isEmpty()){
            System.out.println("Nenhum cliente encontrado");
            return;
        }
        for (Cliente c:clientes){
            System.out.println(c);
        }
    }
    private void buscarClientePorId()throws SQLException{
        System.out.println("\n---Buscar cliente por id");
        int id = lerInt("Informe o ID: ");
        Cliente c = clienteController.buscarCliente(id);
        System.out.println(c == null?"Cliente não encontrado":c.toString());
    }
    private void buscarClientePorNome()throws SQLException{
        System.out.println("\n---Buscar cliente pelo nome---");
        String trecho = lerLinha("Informe o nome do cliente: ");
        List<Cliente> clientes = clienteController.buscarPorNome(trecho);
        if(clientes.isEmpty()){
            System.out.println("Cliente não encontrado");
            return;
        }
        for(Cliente c:clientes){
            System.out.println(c);
        }
    }
    private void atualizarCadastroCliente()throws SQLException{
        System.out.println("\n---Atualizar cadastro do cliente---");
        int id = lerInt("Informe o id: ");
        String nome = lerLinha("Nome cliente: ");
        String email = lerLinha("Email cliente: ");

        boolean ok = clienteController.atualizarCliente(id,nome,email);
        System.out.println(ok?"Atualizado com sucesso":"Id não encontrado");
    }
    private void removerCadastroCliente()throws SQLException{
        System.out.println("\n---Remover cadastro do cliente---");
        int id = lerInt("Informe o id do cliente a ser removido: ");
        boolean ok = clienteController.remover(id);
        System.out.println(ok?"Cliente removido com sucesso":"Id não encontrado");
    }

    private void criarPedido()throws SQLException{
        System.out.println("\n---Criar Pedido---");
        List<ItemEstoque> lista = estoqueController.listarItens();
        for(ItemEstoque i : lista){
            System.out.println(i);
        }
        Integer idProduto = lerInt("Id: ");
        ItemEstoque itemEncontrado = null;
        for(ItemEstoque i : lista){
            if(idProduto.equals(i.getId())){
                itemEncontrado = i;
                break;
            }
        }
        if(itemEncontrado == null){
            System.out.println("Id não encontrado");
            return;
        }
        String descricao = itemEncontrado.getNomeProduto();
        LocalDateTime dataPedido = LocalDateTime.now();
        Integer quantidade = lerInt("Informe a quantidade: ");
        if(quantidade > itemEncontrado.getQuantidade()){
            System.out.println("Quantidade maior");
            return;
        }
        estoqueController.atualizarEstoque(idProduto,quantidade);
        Double valorTot = quantidade * itemEncontrado.getPrecoVenda();
        int id = pedidoController.CriarPedido(descricao,valorTot,dataPedido,quantidade);

        System.out.println("Pedido registrado com sucesso. Id: " + id);
    }
    private void listarPedidos()throws SQLException{
        System.out.println("\n---Listar pedidos---");
        List<Pedido> pedidos = pedidoController.listar();
        if(pedidos.isEmpty()){
            System.out.println("Nenhum pedido encontrado");
            return;
        }
        for(Pedido p:pedidos){
            System.out.println(p);
        }
    }
    private void buscarPedidoPorId()throws SQLException{
        System.out.println("\n---Buscar pedido por ID---");
        int id = lerInt("Informe o ID do pedido: ");
        Pedido p = pedidoController.buscarPedido(id);
        System.out.println(p == null?"Nenhum pedido encontrado":p.toString());
    }

    private void imprimirEtiqueta(Pedido pedido){
        System.out.println("Id: " + pedido.getIdPedido());
        System.out.println("Descrição: " + pedido.getDescricao());
        System.out.println("Quantidade: " + pedido.getQtdProdutosPedido());
        System.out.println("Data: " + pedido.getDataPedido());
    }
    private int lerInt(String prompt){
        while (true){
            System.out.println(prompt);
            String s = sc.nextLine();
            try {
                return Integer.parseInt(s.trim());
            }catch (Exception e){
                System.out.println("Digite um número válido");
            }
        }
    }
    private double lerDouble(String prompt){
        while (true){
            System.out.println(prompt);
            String s = sc.nextLine();
            try {
                return Double.parseDouble(s.trim());
            }catch (Exception e){
                System.out.println("Digite um número decimal válido");
            }
        }
    }
    private String lerLinha(String prompt){
        System.out.println(prompt);
        return sc.nextLine().trim();
    }

}
