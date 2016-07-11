/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import DAO.ContaDAO;
import Util.FXMLUtil;
import Verificacao.VerificarConta;
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
public class FXMLLoginController implements Initializable {
    @FXML private TextField tfUsuario;
    @FXML private TextField tfSenha;
    @FXML private AnchorPane anchorPane;
    private final Stage stageAtual = (Stage)anchorPane.getScene().getWindow();
    private static int id;
    
    @FXML public void logar(ActionEvent event) throws IOException{
        try{
            VerificarConta.verificarUsuario(tfUsuario.getText(),false);
            VerificarConta.verificarSenha(tfSenha.getText());
            id = ContaDAO.logar(tfUsuario.getText(), tfSenha.getText());
            FXMLUtil.abrirJanela("/Menu/MenuMonetizacao/FXMLMenuMonetizacao.fxml", "Menu de Monetização", this, stageAtual, true);
        }catch(Exception ex){
            FXMLUtil.abrirErro(ex, this, stageAtual);
        }
    }
    
    public static int getId(){
        return id;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
