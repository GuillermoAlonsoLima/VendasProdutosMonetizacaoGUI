/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.Conexao;
import java.sql.ResultSet;

/**
 * Executa comandos da tabela produto no banco de dados
 * @author Guillermo1
 */
public class ProdutoDAO {
    
    /**
     * Cadastra o produto com as informações digitadas
     * @param nome nome do produto
     * @param preco preco do produto
     */
    public static void cadastrar(String nome,double preco){
        Conexao.executar("INSERT INTO PRODUTO(NOME,PRECO) VALUES('"+nome+"',"+preco+");");
    }
    
    /**
     * Deleta o produto selecionado
     * @param id ID do produto a deletar
     */
    public static void deletar(int id){
        Conexao.executar("DELETE FROM PRODUTO WHERE ID = "+id+";");
    }
    
    /**
     * Atualiza o produto com as informações digitadas
     * @param id id do produto a atualizar
     * @param nome nome do produto
     * @param preco preco do produto
     */
    public static void atualizar(int id,String nome,double preco){
        Conexao.executar("UPDATE PRODUTO SET NOME = '"+nome+"',PRECO = "+preco+" WHERE ID = "+id+";");
    }
    
    /**
     * Seleciona todos os produtos
     * @return retorna um set com todos os produtos
     */
    public static ResultSet selecionaTudo(){
        return Conexao.selecionar("SELECT P.*,COUNT(PV.QTD) AS VEN FROM PRODUTO P "
                + "JOIN PRODUTO_VENDA PV ON PV.PRODUTO = P.ID "
                + "GROUP BY P.ID;");
    }
    
    /**
     * Seleciona todos os produtos e a quantidade de vendas ordenados pela qtd de vendas em ordem decrescente
     * @return retorna um set com todos produtos ordenados por qtd de vendas
     */
    public static ResultSet selecionarMaisVendidos(){
        return Conexao.selecionar("SELECT P.*,COUNT(PV.QTD) AS VEN FROM PRODUTO P "
                + "JOIN PRODUTO_VENDA PV ON PV.PRODUTO = P.ID "
                + "GROUP BY P.ID "
                + "ORDER BY COUNT(PV.QTD);");
    }
}
