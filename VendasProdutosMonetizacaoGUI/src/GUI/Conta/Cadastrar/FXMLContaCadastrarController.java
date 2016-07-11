/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Conta.Cadastrar;

import DAO.ContaDAO;
import Util.FXMLUtil;
import Verificacao.VerificarConta;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guillermo1
 */
public class FXMLContaCadastrarController implements Initializable {
    @FXML private TextField tfUsuario;
    @FXML private PasswordField pfSenha;
    @FXML private TextField tfSaldo;
    @FXML private final ChoiceBox<Integer> cbCliente = new ChoiceBox<>();
    @FXML private AnchorPane anchorPane;
    private final Stage stageAtual = (Stage)anchorPane.getScene().getWindow();
    private final ObservableList<Integer> clientes = FXCollections.observableArrayList();
    private Exception exception;
    private int id;
    
    @FXML public void cadastrar(ActionEvent event) throws IOException{
        try{
            if(exception != null){
                throw exception;
            }
            VerificarConta.verificarUsuario(tfUsuario.getText(),true);
            VerificarConta.verificarSenha(pfSenha.getText());
            Double.parseDouble(tfSaldo.getText());
            ContaDAO.cadastrar(tfUsuario.getText(),pfSenha.getText(),tfSaldo.getText());
        }catch(Exception e){
            FXMLUtil.abrirErro(e, this, stageAtual);
        }
    }
    
    public void preencherChoiceBox() throws Exception{
        ResultSet res = ContaDAO.selecionaTudo();
        if(!res.isBeforeFirst())
            throw new Exception("Não há clientes cadastrados!");
        while(res.next()){
            clientes.add(res.getInt(1));
        }
        cbCliente.setItems(clientes);
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            preencherChoiceBox();
        } catch (Exception ex) {
            exception = ex;
            try {
                FXMLUtil.abrirErro(ex, this, stageAtual);
            } catch (IOException ex1) {
                Logger.getLogger(FXMLContaCadastrarController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }    
    
}
