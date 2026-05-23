package edu.br.log.model;

public class ItemEstoque {
    private Integer id;
    private String nomeProduto;
    private String sku;
    private String ean;
    private Integer quantidade;
    private Double precoVenda;

    public ItemEstoque(){}
    public ItemEstoque(Integer id, String nomeProduto, String sku, String ean, Integer quantidade) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.sku = sku;
        this.ean = ean;
        this.quantidade = quantidade;
    }
    public ItemEstoque(String nomeProduto, String sku, String ean, Integer quantidade, Double precoVenda) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.sku = sku;
        this.ean = ean;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }
    public void addItemEstoque(Integer quantidade){
        if(quantidade == null || quantidade <=0){
            throw new IllegalArgumentException("A quantidade a ser adicionada deve ser maior que 0!");
        }else {
            this.quantidade += quantidade;
        }
    }
    public void removerItemEstoque(Integer quantidade){
        if(quantidade == null || quantidade <= 0){
            throw new IllegalArgumentException("A quantidade a ser removida deve ser maior que 0");
        }
        if(quantidade > this.quantidade){
            throw new IllegalArgumentException("Saldo insuficiente. Disponível: " + this.quantidade);
        }
        this.quantidade -= quantidade;
    }
    public boolean estoqueDisponivel(){
        return this.quantidade >0;
    }


}
