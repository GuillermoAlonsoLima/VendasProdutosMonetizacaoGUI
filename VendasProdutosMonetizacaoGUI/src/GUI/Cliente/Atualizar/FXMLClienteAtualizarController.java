/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Cliente.Atualizar;

import Conexao.Conexao;
import DAO.ClienteDAO;
import GUI.Cliente.Selecionar.FXMLClienteSelecionarController;
import Util.FXMLUtil;
import Verificacao.VerificarCliente;
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
public class FXMLClienteAtualizarController implements Initializable {
    @FXML private TextField tfCpf;
    @FXML private TextField tfNome;
    @FXML private TextField tfEmail;
    @FXML private AnchorPane anchorPane;
    private final Stage stageAtual = (Stage)anchorPane.getScene().getWindow();
    private int id;
    
    @FXML public void atualizar(ActionEvent event) throws IOException{
        try{
            VerificarCliente.verificarCpf(tfCpf.getText(), true);
            VerificarCliente.verificarNome(tfNome.getText());
            VerificarCliente.verificarEmail(tfEmail.getText());
            ClienteDAO.atualizar(id,Long.parseLong(tfCpf.getText()), tfNome.getText(), tfEmail.getText());
        }catch(Exception e){
            FXMLUtil.abrirErro(e, this, stageAtual);
        }
    }
    
    public void inicializarValores() throws SQLException{
        ResultSet res = Conexao.selecionar("SELECT * FROM CLIENTE WHERE ID = "+id+";");
        while(res.next()){
            tfCpf.setText(res.getString(2));
            tfNome.setText(res.getString(3));
            tfEmail.setText(res.getString(4));
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
