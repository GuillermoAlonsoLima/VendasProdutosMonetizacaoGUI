/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Cliente.Cadastrar;

import DAO.ClienteDAO;
import Util.FXMLUtil;
import Verificacao.VerificarCliente;
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
public class FXMLClienteCadastrarController implements Initializable {
    @FXML private TextField tfCpf;
    @FXML private TextField tfNome;
    @FXML private TextField tfEmail;
    @FXML private AnchorPane anchorPane;
    private final Stage stageAtual = (Stage)anchorPane.getScene().getWindow();
    
    @FXML public void cadastrar(ActionEvent event) throws IOException{
        try{
            VerificarCliente.verificarCpf(tfCpf.getText(), true);
            VerificarCliente.verificarNome(tfNome.getText());
            VerificarCliente.verificarEmail(tfEmail.getText());
            ClienteDAO.cadastrar(Long.parseLong(tfCpf.getText()), tfNome.getText(), tfEmail.getText());
        }catch(Exception e){
            FXMLUtil.abrirErro(e, this, stageAtual);
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
