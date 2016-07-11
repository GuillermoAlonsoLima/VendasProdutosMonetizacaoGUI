package Conexao;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** 
 * possui os métodos para conexao com o banco e execução de comandos sql
 * @author Guillermo1
 */
public class Conexao {
    private static Statement stm;
    
    /** 
     * Conecta com o banco de dados e cria o statement
     */
    public static void conexao() {
        try {
            Class.forName("org.postgresql.Driver");
            stm = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Monetizacao", "postgres", "123456").createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /** 
     * Executa um comando sql
     * @param sql comando a executar
     */
    public static void executar(String sql){
        try {
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /** 
     * retorna um set de dados selecionados no banco
     * @param sql comando a executar
     * @return set de dados selecionados
     */
    public static ResultSet selecionar(String sql){
        try {
            return stm.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
