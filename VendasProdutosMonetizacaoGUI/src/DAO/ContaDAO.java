/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.Conexao;
import java.sql.ResultSet;

/**
 * Executa comandos da tabela conta no banco de dados
 * @author Guillermo1
 */
public class ContaDAO {

    /**
     * Cadastra a conta com as informações digitadas
     * @param usuario usuario da conta
     * @param senha senha da conta
     * @param saldo saldo da conta
     * @param cliente cliente da conta
     */
    public static void cadastrar(String usuario, String senha,double saldo,int cliente){
        Conexao.executar("INSERT INTO CONTA(USUARIO,SENHA,SALDO,CLIENTE) VALUES('"+usuario+"','"+senha+"',"+saldo+","+cliente+");");
    }
    
    /**
     * Deleta a conta selecionada
     * @param id ID da conta a deletar
     */
    public static void deletar(int id){
        Conexao.executar("DELETE FROM CONTA WHERE ID = "+id+";");
    }
    
    /**
     * Atualiza a conta com as informações digitadas
     * @param id id da conta a atualizar
     * @param usuario usuario da conta
     * @param senha senha da conta
     * @param saldo saldo da conta
     * @param cliente cliente da conta
     */
    public static void atualizar(int id,String usuario, String senha,double saldo,int cliente){
        Conexao.executar("UPDATE CONTA SET USUARIO = '"+usuario+"',SENHA = '"+senha+"',SALDO = "+saldo+",CLIENTE = "+cliente+" WHERE ID = "+id+";");
    }
    
    /**
     * Seleciona todas as contas
     * @return retorna um set com todas as contas
     */
    public static ResultSet selecionaTudo(){
        return Conexao.selecionar("SELECT CO.*,COUNT(V.*) AS COM,COUNT(M.*) AS MON FROM CONTA CO "
                + "JOIN VENDA V ON V.CONTA = CO.ID "
                + "JOIN MONETIZACAO M ON M.CONTA = CO.ID "
                + "GROUP BY CL.ID;");
    }
    
    /**
     * Seleciona todas as contas e a qtd de compras ordenado por qtd de compras em ordem decrescente
     * @return retorna um set com todas as contas ordenado por compra
     */
    public static ResultSet selecionaMaisCompram(){
        return Conexao.selecionar("SELECT CO.*,COUNT(V.*) AS COM,COUNT(M.*) AS MON FROM CONTA CO "
                + "JOIN VENDA V ON V.CONTA = CO.ID "
                + "JOIN MONETIZACAO M ON M.CONTA = CO.ID "
                + "GROUP BY CL.ID "
                + "ORDER BY COUNT(V.*);");
    }
    /**
     * Seleciona todas as contas e a qtd de monetizações ordenado por qtd de monetizações em ordem decrescente
     * @return retorna um set com todas as contas ordenado por monetizações feitas
     */
    public static ResultSet selecionaMaisMonetiza(){
        return Conexao.selecionar("SELECT CO.*,COUNT(V.*) AS COM,COUNT(M.*) AS MON FROM CONTA CO "
                + "JOIN VENDA V ON V.CONTA = CO.ID "
                + "JOIN MONETIZACAO M ON M.CONTA = CO.ID "
                + "GROUP BY CL.ID "
                + "ORDER BY COUNT(M.*);");
    }
    
    /**
     * Loga a conta
     * @param usuario usuario a logar
     * @param senha senha a logar
     * @return retorna o id da conta logada
     * @throws Exception Se não houver nenhuma conta correspondente
     */
    public static int logar(String usuario,String senha) throws Exception{
        ResultSet res = Conexao.selecionar("SELECT ID FROM CONTA WHERE USUARIO = '"+usuario+"',SENHA = '"+senha+"';");
        if(!res.isBeforeFirst()){
            throw new Exception("Usuário ou senha inválidos!");
        }else{
            while(res.next()){
                return res.getInt(1);
            }
        }
        return 0;
    }
    
}
