/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.Conexao;
import java.sql.ResultSet;

/**
 * Executa comandos da tabela cliente no banco de dados
 * @author Guillermo1
 */
public class ClienteDAO {

    /**
     * Cadastra o cliente com as informações digitadas
     * @param cpf CPF do cliente
     * @param nome Nome do cliente
     * @param email E-mail do cliente
     */
    public static void cadastrar(long cpf,String nome,String email){
        Conexao.executar("INSERT INTO CLIENTE(CPF,NOME,EMAIL) VALUES("+cpf+",'"+nome+"','"+email+"');");
    }
    
    /**
     * Deleta o cliente selecionado
     * @param id ID do cliente a deletar
     */
    public static void deletar(int id){
        Conexao.executar("DELETE FROM CLIENTE WHERE ID = "+id+";");
    }
    
    /**
     * Atualiza o cliente com as informações digitadas
     * @param id ID do cliente a atualizar
     * @param cpf CPF do cliente
     * @param nome Nome do cliente
     * @param email E-mail do cliente
     */
    public static void atualizar(int id,long cpf,String nome,String email){
        Conexao.executar("UPDATE CLIENTE SET CPF = "+cpf+",NOME = '"+nome+"',EMAIL = '"+email+"' WHERE ID = "+id+";");
    }
    
    /**
     * Seleciona todos os clientes
     * @return retorna um set com todos os clientes
     */
    public static ResultSet selecionaTudo(){
        return Conexao.selecionar("SELECT CL.*,COUNT(V.*) AS COM,COUNT(M.*) AS MON FROM CLIENTE CL "
                + "JOIN CONTA CO ON CO.CLIENTE = CL.ID "
                + "JOIN VENDA V ON V.CONTA = CO.ID "                
                + "JOIN MONETIZACAO M ON M.CONTA = CO.ID "
                + "GROUP BY CL.ID;");
    }

    /**
     * Seleciona todos os clientes e a qtd de compras ordenado por qtd de compras em ordem decrescente
     * @return retorna um set com todos os clientes ordenado por compra
     */
    public static ResultSet selecionaMaisCompram(){
        return Conexao.selecionar("SELECT CL.*,COUNT(V.*) AS COM,COUNT(M.*) AS MON FROM CLIENTE CL "
                + "JOIN CONTA CO ON CO.CLIENTE = CL.ID "
                + "JOIN VENDA V ON V.CONTA = CO.ID "                
                + "JOIN MONETIZACAO M ON M.CONTA = CO.ID "
                + "GROUP BY CL.ID "
                + "ORDER BY COUNT(V.*);");
    }
    
    /**
     * Seleciona todos os clientes e a qtd de monetizações ordenado por qtd de monetizações em ordem decrescente
     * @return retorna um set com todos os clientes ordenado por monetização
     */
    public static ResultSet selecionaMaisMonetiza(){
        return Conexao.selecionar("SELECT CL.*,COUNT(V.*) AS COM,COUNT(M.*) AS MON FROM CLIENTE CL "
                + "JOIN CONTA CO ON CO.CLIENTE = CL.ID "
                + "JOIN VENDA V ON V.CONTA = CO.ID "     
                + "JOIN MONETIZACAO M ON M.CONTA = CO.ID "
                + "GROUP BY CL.ID "
                + "ORDER BY COUNT(M.*);");
    }
    
}
