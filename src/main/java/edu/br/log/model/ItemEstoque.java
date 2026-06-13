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
        this.nomeProduto = nomeProduto;
        this.sku = sku;
        this.ean = ean;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    public ItemEstoque(Integer id, String nomeProduto, String sku, String ean, Integer quantidade, Double precoVenda) {
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

    @Override
    public String toString() {
        return "ItemEstoque{" +
                "id=" + id +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", sku='" + sku + '\'' +
                ", ean='" + ean + '\'' +
                ", quantidade=" + quantidade +
                ", precoVenda=" + precoVenda +
                '}';
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }



}
