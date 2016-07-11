/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Menu.MenuMonetizacao;

import Util.FXMLUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guillermo1
 */
public class FXMLMenuMonetizacaoController implements Initializable {
    @FXML private AnchorPane anchorPane;
    private final Stage stageAtual = (Stage)anchorPane.getScene().getWindow();
    
    @FXML public void abrirCompra() throws IOException{
        FXMLUtil.abrirJanela("/Monetizacao/Comprar/FXMLComprar.fxml", "Comprar", this, stageAtual, true);
    }
    
    @FXML public void abrirDepositar() throws IOException{
        FXMLUtil.abrirJanela("/Monetizacao/Comprar/FXMLDepositar.fxml", "Depositar", this, stageAtual, true);
    }
    
    @FXML public void abrirRetirar() throws IOException{
        FXMLUtil.abrirJanela("/Monetizacao/Comprar/FXMLRetirar.fxml", "Retirar", this, stageAtual, true);
    }
    
    @FXML public void abrirTransferir() throws IOException{
        FXMLUtil.abrirJanela("/Monetizacao/Comprar/FXMLTransferirfxml", "Transferir", this, stageAtual, true);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
