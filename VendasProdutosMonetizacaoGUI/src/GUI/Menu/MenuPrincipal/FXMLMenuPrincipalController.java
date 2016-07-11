/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Menu.MenuPrincipal;

import Util.FXMLUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guillermo1
 */
public class FXMLMenuPrincipalController implements Initializable {
    @FXML private AnchorPane anchorPane;
    private Stage stageAtual;
    
    @FXML public void abrirCliente(ActionEvent event){
        try{
            stageAtual = (Stage)anchorPane.getScene().getWindow();
            FXMLUtil.abrirJanela("/Cliente/Selecionar/FXMLClienteSelecionar.fxml", "Cliente", this, stageAtual, true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML public void abrirConta(ActionEvent event){
        try{
            stageAtual = (Stage)anchorPane.getScene().getWindow();
            FXMLUtil.abrirJanela("/Conta/Selecionar/FXMLContaSelecionar.fxml", "Conta", this, stageAtual, true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML public void abrirProduto(ActionEvent event){
        try{
            stageAtual = (Stage)anchorPane.getScene().getWindow();
            FXMLUtil.abrirJanela("/Produto/Selecionar/FXMLProdutoSelecionar.fxml", "Produto", this, stageAtual, true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML public void abrirVendas(ActionEvent event){
        try{
            stageAtual = (Stage)anchorPane.getScene().getWindow();
            FXMLUtil.abrirJanela("/Vendas/Selecionar/FXMLVendasSelecionar.fxml", "Vendas", this, stageAtual, true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML public void abrirLogin(ActionEvent event){
        try{
            stageAtual = (Stage)anchorPane.getScene().getWindow();
            FXMLUtil.abrirJanela("/Login/FXMLLogin.fxml", "Vendas", this, stageAtual, true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
