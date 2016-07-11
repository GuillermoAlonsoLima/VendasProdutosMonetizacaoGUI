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
 *
 * @author Guillermo1
 */
public class VerificarConta {
    
    /**
     * Verifica se o usuario não está vazio
     * @param usuario usuario a verificar
     * @param cadastro se true, está se tentando cadastrar, portanto este conta não deve existir, se false, está se tentando selecionar, portanto este conta deve existir 
     * @throws Exception se uma das condições estiver incorreta
     */
    public static void verificarUsuario(String usuario,boolean cadastro) throws Exception{
        if(usuario.isEmpty())
            throw new Exception("Usuario está vazio!");
        else if(contaExiste(usuario) && cadastro == true)
            throw new Exception("Conta já existe!");
        else if(!contaExiste(usuario) && cadastro == false)
            throw new Exception("Conta não existe!");
    }
    
    /**
     * Verifica se a senha está vazia, se ela tem letras e números
     * @param senha senha a verificar
     * @throws Exception se uma das condições estiver incorreta
     */
    public static void verificarSenha(String senha) throws Exception{
        if(senha.isEmpty())
            throw new Exception("Senha está vazia!");
        else if(!senha.matches(".*[a-zA-Z]+.*"))
            throw new Exception("Senha deve ter letras!");
        else if(!senha.matches(".*[0-9]+.*"))
            throw new Exception("Senha deve ter números!");
    }
    
    /**
     * Verifica se conta existe
     * @param id id a procurar
     * @return true se existir, false se não existir
     * @throws SQLException Erro no SQL
     */
    public static boolean contaExiste(int id) throws SQLException{
        ResultSet conta = Conexao.selecionar("SELECT * FROM CONTA WHERE ID = "+id+";");
        return conta.isBeforeFirst();
    }
    
    /**
     * Verifica se conta existe
     * @param usuario usuario a procurar
     * @return true se existir, false se não existir
     * @throws SQLException Erro no SQL
     */
    public static boolean contaExiste(String usuario) throws SQLException{
        ResultSet conta = Conexao.selecionar("SELECT * FROM CONTA WHERE USUARIO = "+usuario+";");
        return conta.isBeforeFirst();
    }
    
}
