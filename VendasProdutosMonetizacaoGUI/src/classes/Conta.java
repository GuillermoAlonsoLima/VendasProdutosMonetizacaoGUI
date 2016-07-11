/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Guillermo1
 */
public class Conta {
    private int id,mon,com;
    private String usuario;
    private String senha;
    private double saldo;
    private int cliente;

    public Conta(int id, String usuario, String senha, double saldo, int cliente, int mon, int com) {
        this.id = id;
        this.mon = mon;
        this.com = com;
        this.usuario = usuario;
        this.senha = senha;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getMoentizacoes() {
        return mon;
    }

    public void setMonetizacoes(int vendas) {
        this.mon = vendas;
    }

    public int getCompras() {
        return com;
    }

    public void setCompras(int compras) {
        this.com = compras;
    }
    
    
}
