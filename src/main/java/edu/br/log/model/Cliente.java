package edu.br.log.model;

import java.util.List;

public class Cliente {
    private Integer id;
    private String nomeUsuario;
    private String emailUsuario;
    private List<Pedido> pedidoUsuario;

    public Cliente(){}

    public Cliente(Integer id, String nomeUsuario, String emailUsuario) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
    }

    public Cliente(String nomeUsuario, String emailUsuario){
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
    }

    public Cliente(Integer id,String nomeUsuario, String emailUsuario, List<Pedido> pedidoUsuario) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.pedidoUsuario = pedidoUsuario;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public List<Pedido> getPedidoUsuario() {
        return pedidoUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", emailUsuario='" + emailUsuario + '\'' +
                ", pedidoUsuario=" + pedidoUsuario +
                '}';
    }
}
