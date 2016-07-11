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
public class VerificarProduto {
    /**
     * Verifica se produto existe
     * @param id ID do produto a procurar
     * @return true se existir, false se não existir
     * @throws SQLException Erro no SQL
     */
    public static boolean produtoExiste(int id) throws SQLException{
        ResultSet produto = Conexao.selecionar("SELECT * FROM PRODUTO WHERE ID = "+id+";");
        return produto.isBeforeFirst();
    }
}
