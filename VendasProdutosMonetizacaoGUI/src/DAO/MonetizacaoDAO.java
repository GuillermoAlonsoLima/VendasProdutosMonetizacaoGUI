package DAO;

import Conexao.Conexao;
import java.sql.ResultSet;
import java.sql.SQLException;

/** MonetizacaoDAO
 * Possui métodos de monetização
 * @author Guillermo1
 */
public class MonetizacaoDAO {

    /**
     * Verifica se a conta com os respectivos usuario e senha existe
     * @param usuario Usuario da conta
     * @param senha Senha da conta
     * @return Retorna o id da conta se encontrado
     * @throws SQLException Se não encontrar, joga uma exception pois quer dizer que os dados estão incorretos ou a conta não existe 
     */
    public static Integer logar(String usuario,String senha) throws SQLException{
        ResultSet res = Conexao.selecionar("SELECT ID FROM CONTA WHERE USUARIO = '"+usuario+"' AND SENHA = '"+senha+"';");
        if(!res.isBeforeFirst())
            throw new SQLException("Usuario e/ou senha incorretos!");
        else{
            while(res.next()){
                return res.getInt(1);
            }
        }
        return null;
    }
    
    /** depositar
     * deposita saldo na conta
     * @param conta conta a depositar
     * @param saldo quantia a depositar
     */
    public static void depositar(Integer conta,String saldo){
        Conexao.executar("UPDATE CONTA SET SALDO = SALDO + "+saldo+" WHERE ID = "+conta+";");
    }
    
    /** retirar
     * retira saldo da conta 
     * @param conta conta a retirar
     * @param saldo quantia a retirar
     * @throws SQLException Se a quantia a retirar é maior que a depositada
     */
    public static void retirar(Integer conta,String saldo) throws SQLException{
        ResultSet selecao = Conexao.selecionar("SELECT SALDO FROM CONTA WHERE ID = "+conta+";");
        while(selecao.next()){
            if(selecao.getDouble(1)<Double.parseDouble(saldo))
                throw new SQLException("Saldo insuficiente!");
            else
                Conexao.executar("UPDATE CONTA SET SALDO = SALDO - "+saldo+" WHERE ID = "+conta+";");
        }       
    }
    
    /** transferir
     * transfere o saldo de uma conta para outra se o saldo depositado for maior ou igual ao retirado
     * @param conta conta a retirar
     * @param usuario conta a depositar
     * @param saldo quantia a transferir
     * @throws SQLException Se a quantia a transferir é maior que a depositada
     */
    public static void transferir(Integer conta,String usuario,String saldo) throws SQLException{
        ResultSet selecao = Conexao.selecionar("SELECT SALDO FROM CONTA WHERE ID = "+conta+";");
        while(selecao.next()){
            if(selecao.getDouble(1)<Double.parseDouble(saldo))
                throw new SQLException("Saldo insuficiente!");
            else{
                Conexao.executar("UPDATE CONTA SET SALDO = SALDO - "+saldo+" WHERE ID = "+conta+";"
                        + "UPDATE CONTA SET SALDO = SALDO + "+saldo+" WHERE USUARIO = '"+usuario+"';");
            }
        }
    }
    
    public static double visualizarSaldo(int id) throws SQLException{
        ResultSet selecao = Conexao.selecionar("SELECT SALDO FROM CONTA WHERE ID = "+id+";");
        while(selecao.next()){
            return selecao.getDouble(1);
        }
        return 0;
    }
}
