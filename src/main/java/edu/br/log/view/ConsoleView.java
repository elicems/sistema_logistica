package edu.br.log.view;

import edu.br.log.controller.EstoqueController;
import edu.br.log.model.ItemEstoque;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private final EstoqueController controller = new EstoqueController();
    private final Scanner sc = new Scanner(System.in);

    public void inicar(){
        int op;
        do{
            mostrarMenu();
            op = lerInt("Escolha: ");

            try {
                switch (op){
                    case 1 -> criar();
                    case 2 -> listar();
                    case 3 -> buscarPorId();
                    case 4 -> buscarPorNome();
                    case 5 -> atualizar();
                    case 6 -> remover();
                    case 0 -> System.out.println("Encerrando...");
                    default -> System.out.println("Opção invalida");
                }
            }catch (IllegalArgumentException e){
                System.out.println("Erro de validação: " + e.getMessage());
            }catch (SQLException e){
                System.out.println("Erro de banco de dados: " + e.getMessage());
            }catch (Exception e){
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }while (op != 0);
    }
    private void mostrarMenu() {
        System.out.println("\n=== CRUD Contatos (MySQL) ===");
        System.out.println("1) Criar contato");
        System.out.println("2) Listar contatos");
        System.out.println("3) Buscar contato por ID");
        System.out.println("4) Buscar contato por nome (trecho)");
        System.out.println("5) Atualizar contato");
        System.out.println("6) Remover contato");
        System.out.println("0) Sair");
    }
    private void criar()throws SQLException{
        System.out.println("\n---Adicionar item ao estoque---");
        String nomeProduto = lerLinha("Nome do produto: ");
        String sku = lerLinha("SKU do produto: ");
        String ean = lerLinha("EAN do produto: ");
        Integer quantidade = lerInt("Quantidade: ");
        Double precoVenda = lerDouble("Preço Venda: ");

        int id = controller.criarItemEstoque(nomeProduto,sku,ean,quantidade,precoVenda);
        System.out.println("Item adicionado com id: " + id);
    }
    private void listar()throws SQLException{
        System.out.println("\n---Listar itens---");
        List<ItemEstoque> itens = controller.listarItens();
        if(itens.isEmpty()){
            System.out.println("Nenhum item encontrado");
            return;
        }
        for (ItemEstoque i:itens){
            System.out.println(i);
        }
    }
    private void buscarPorId()throws SQLException{
        System.out.println("\n---Buscar por id---");
        int id = lerInt("Id: ");
        ItemEstoque i = controller.buscarItem(id);
        System.out.println(i == null ? "Não encontrado. " : i.toString());
    }
    private void buscarPorNome()throws SQLException{
        System.out.println("\n---Buscar por nome---");
        String trecho = lerLinha("Trecho do nome: ");
        List<ItemEstoque> itens = controller.buscarPorNome(trecho);
        if(itens.isEmpty()){
            System.out.println("Nenhum item encontrado.");
            return;
        }
        for(ItemEstoque i:itens){
            System.out.println(i);
        }
    }
    private void atualizar()throws SQLException{
        System.out.println("\n---Atualizar item---");
        int id = lerInt("ID: ");
        String nomeProduto = lerLinha("Novo nome: ");
        String sku = lerLinha("Novo SKU: ");
        String ean = lerLinha("Novo EAN: ");
        Integer quantidade = lerInt("Nova quantidade: ");
        Double precoVenda = lerDouble("Novo preco: ");

        boolean ok = controller.atualizarItem(id,nomeProduto,sku,ean,quantidade,precoVenda);
        System.out.println(ok?"Atualizado com sucesso":"Id não encontrado.");
    }
    public void remover()throws SQLException{
        System.out.println("\n---Remover item---");
        int id = lerInt("ID: ");
        boolean ok = controller.remover(id);
        System.out.println(ok?"Removido com sucesso.":"ID não encontrado.");
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
