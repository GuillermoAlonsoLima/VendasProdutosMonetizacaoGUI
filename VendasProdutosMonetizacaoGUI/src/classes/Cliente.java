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
public class Cliente {
    private int id;
    private int mon,com;
    private long cpf;
    private String nome;
    private String email;

    public Cliente(int id,long cpf, String nome, String email,int com, int mon) {
        this.id = id;
        this.mon = mon;
        this.com = com;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMon() {
        return mon;
    }

    public void setMon(int mon) {
        this.mon = mon;
    }

    public int getCom() {
        return com;
    }

    public void setCom(int com) {
        this.com = com;
    }
    
    
}
