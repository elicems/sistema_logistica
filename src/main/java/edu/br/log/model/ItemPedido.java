package edu.br.log.model;

import java.util.List;

public class ItemPedido{
    private Integer id;
    private ItemEstoque produto;
    private Integer quantidade;
    private Double precoTotal;

    public ItemPedido() {
    }

    public ItemPedido(ItemEstoque produto, Integer quantidade, Double precoTotal) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ItemEstoque getProduto() {
        return produto;
    }

    public void setProduto(ItemEstoque produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoTotal +
                '}';
    }
}
