/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Monetizacao.Retirar;

import DAO.MonetizacaoDAO;
import GUI.Login.FXMLLoginController;
import Util.FXMLUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
public class FXMLRetirarController implements Initializable {
    @FXML private TextField tfValor;
    private int id;
    @FXML private AnchorPane anchorPane;
    private final Stage stageAtual = (Stage)anchorPane.getScene().getWindow();
    
    @FXML public void retirar(ActionEvent event) throws IOException{
        try{
            MonetizacaoDAO.retirar(id, tfValor.getText());
        }catch(Exception e){
            FXMLUtil.abrirErro(e, this, stageAtual);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id = FXMLLoginController.getId();
    }   
       
}
