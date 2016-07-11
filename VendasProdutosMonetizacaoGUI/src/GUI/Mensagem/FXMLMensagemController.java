/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Mensagem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Guillermo1
 */
public class FXMLMensagemController implements Initializable {
    @FXML Label lblErro;
    private static String message;
    
    public void setErro(){
        lblErro.setText(message);
    }
    
    public void setMessage(String erro){
        message = erro;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setErro();
    }    
    
}
