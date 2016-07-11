/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Verificacao;

import Conexao.Conexao;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contém métodos de verificação dos dados do cliente
 * @author Guillermo1
 */
public class VerificarCliente {

    /**
     * Verifica se o cpf não está vazio, tem 11 números e não tem letras
     * @param cpf cpf a verificar
     * @param cadastro se true, está se tentando cadastrar, portanto este cliente não deve existir, se false, está se tentando selecionar, portanto este cliente deve existir 
     * @throws Exception se uma das verificações acima estiver incorreta
     */
    public static void verificarCpf(String cpf,boolean cadastro) throws Exception{
        if(cpf.isEmpty())
            throw new Exception("CPF está vazio!");
        else if(cpf.length()!=11)
            throw new Exception("CPF deve ter 11 números!");
        else if(!cpf.matches("[0-9]+"))
            throw new Exception("CPF deve ter somente números!");
        else if(clienteExiste(cpf) && cadastro == true)
            throw new Exception("Cliente já existe!");
        else if(!clienteExiste(cpf) && cadastro == false)
            throw new Exception("Cliente não existe!");
    }
    
    /**
     * verifica se o nome não está  vazio, se começou em maiúsculo, se tem 3 palavras e se não tem números
     * @param nome nome a verificar
     * @throws Exception se uma das verificações acima estiver incorreta
     */
    public static void verificarNome(String nome) throws Exception{
        if(nome.isEmpty())
            throw new Exception("Nome está vazio!");
        else if(!Character.isUpperCase(nome.charAt(0)))
            throw new Exception("Nome deve começar em maiúsculo!");
        else if(nome.split("\\s+").length < 3)
            throw new Exception("Nome incompleto!");
        else if(nome.matches(".*\\d+.*"))
            throw new Exception("Nome não deve ter números!");
    }
    
    /**
     * verifica se o email não está vazio, se seu formato é válido e se está em minúsculo
     * @param email email a verificar
     * @throws Exception se uma das verificações acima estiver incorreta
     */
    public static void verificarEmail(String email) throws Exception{
        if(email.isEmpty())
            throw new Exception("E-mail está vazio!");
        else if(!email.matches("^(.+)@(.+)$"))
            throw new Exception("E-mail inválido!");
        else if(!email.matches(email.toLowerCase()))
            throw new Exception("Email deve estar em minúsculo!");
    }
    
    /**
     * Verifica se o cliente existe
     * @param cpf cpf a encontrar
     * @return true se o cliente existir, false se não existir
     * @throws SQLException erro de sql
     */
    public static boolean clienteExiste(String cpf) throws SQLException{
        ResultSet cliente = Conexao.selecionar("SELECT * FROM CLIENTE WHERE CPF = '"+cpf+"';");
        return cliente.isBeforeFirst();
    }
    
    /**
     * Verifica se o cliente existe
     * @param id id a encontrar
     * @return true se o cliente existir, false se não existir
     * @throws SQLException erro de sql
     */
    public static boolean clienteExiste(int id) throws SQLException{
        ResultSet cliente = Conexao.selecionar("SELECT * FROM CLIENTE WHERE ID = "+id+";");
        return cliente.isBeforeFirst();
    }
}
