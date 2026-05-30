package edu.br.log.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Pedido {
    private Integer idPedido;
    private String descricao;
    private Double valorTotal;
    private Integer clienteId;
    private LocalDateTime dataPedido;
    private Integer qtdProdutosPedido;

    private List<ItemPedido> itens;

    public Pedido() {
    }

    public Pedido(String descricao,Double valorTotal,LocalDateTime dataPedido,Integer qtdProdutosPedido) {
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.dataPedido = dataPedido;
        this.qtdProdutosPedido = qtdProdutosPedido;
    }

    public Pedido(Integer idPedido,String descricao, Double valorTotal, Integer clienteId, List<ItemPedido> itens) {
        this.idPedido = idPedido;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.clienteId = clienteId;
        this.itens = itens;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Integer getQtdProdutosPedido() {
        return qtdProdutosPedido;
    }

    public void setQtdProdutosPedido(Integer qtdProdutosPedido) {
        this.qtdProdutosPedido = qtdProdutosPedido;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", valorTotal=" + valorTotal +
                ", clienteId=" + clienteId +
                ", itens=" + itens +
                '}';
    }
}
