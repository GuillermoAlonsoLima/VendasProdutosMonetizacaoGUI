/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produto.Atualizar;

import Conexao.Conexao;
import DAO.ProdutoDAO;
import GUI.Cliente.Atualizar.FXMLClienteAtualizarController;
import GUI.Cliente.Selecionar.FXMLClienteSelecionarController;
import Util.FXMLUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guillermo1
 */
public class FXMLProdutoAtualizarController implements Initializable {

    @FXML private TextField tfNome;
    @FXML private TextField tfPreco;
    @FXML private AnchorPane anchorPane;
    private final Stage stageAtual = (Stage)anchorPane.getScene().getWindow();
    private int id;
    
    @FXML public void cadastrar(ActionEvent event) throws IOException{
        try{            
            ProdutoDAO.cadastrar(tfNome.getText(),Double.parseDouble(tfPreco.getText()));
        }catch(Exception e){
            FXMLUtil.abrirErro(e, this, stageAtual);
        }
    }
    
    public void inicializarValores() throws SQLException{
        ResultSet res = Conexao.selecionar("SELECT * FROM PRODUTO WHERE ID = "+id+";");
        while(res.next()){
            tfNome.setText(res.getString(2));
            tfPreco.setText(res.getString(3));
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id = FXMLClienteSelecionarController.getId();
        try {
            inicializarValores();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLClienteAtualizarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
