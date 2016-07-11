/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Cliente.Selecionar;

import DAO.ClienteDAO;
import Util.FXMLUtil;
import Verificacao.VerificarCliente;
import classes.Cliente;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guillermo1
 */
public class FXMLClienteSelecionarController implements Initializable {
    @FXML private final TableView<Cliente> tabela = new TableView<>();
    @FXML private final TableColumn<Cliente,String> tcId = new TableColumn<>("ID");
    @FXML private final TableColumn<Cliente,String> tcCpf = new TableColumn<>("CPF");
    @FXML private final TableColumn<Cliente,String> tcNome = new TableColumn<>("Nome");
    @FXML private final TableColumn<Cliente,String> tcEmail = new TableColumn<>("Email");    
    @FXML private final TableColumn<Cliente,String> tcCom = new TableColumn<>("Com");
    @FXML private final TableColumn<Cliente,String> tcMon = new TableColumn<>("Mon");
    @FXML private TextField tfId;
    @FXML private AnchorPane anchorPane;
    private final Stage stageAtual = (Stage)anchorPane.getScene().getWindow();
    private final ObservableList<Cliente> clientes = FXCollections.observableArrayList();
    private static int id;
    
    public void inicializarTabela(){
        tcId.setCellFactory(new PropertyValueFactory("id"));
        tcCpf.setCellFactory(new PropertyValueFactory("cpf"));
        tcNome.setCellFactory(new PropertyValueFactory("nome"));
        tcEmail.setCellFactory(new PropertyValueFactory("email"));
        tcMon.setCellFactory(new PropertyValueFactory("mon"));
        tcCom.setCellFactory(new PropertyValueFactory("com"));
    }
    
    @FXML public void colocarTudoTabela() throws SQLException{
        ResultSet res = ClienteDAO.selecionaTudo();
        while(res.next()){
            clientes.add(new Cliente(res.getInt(1),res.getLong(2),res.getString(3),res.getString(4),res.getInt(5),res.getInt(6)));
        }
        tabela.setItems(clientes);
        tabela.getColumns().addAll(tcId,tcCpf,tcNome,tcEmail,tcMon,tcCom);
    }
    
    @FXML public void colocarTudoTabela(ActionEvent event) throws SQLException{
        ResultSet res = ClienteDAO.selecionaTudo();
        while(res.next()){
            clientes.add(new Cliente(res.getInt(1),res.getLong(2),res.getString(3),res.getString(4),res.getInt(5),res.getInt(6)));
        }
        tabela.setItems(clientes);
        tabela.getColumns().addAll(tcId,tcCpf,tcNome,tcEmail,tcMon,tcCom);
    }
    
    @FXML public void colocarMaisCompram(ActionEvent event) throws SQLException{
        ResultSet res = ClienteDAO.selecionaMaisCompram();
        while(res.next()){
            clientes.add(new Cliente(res.getInt(1),res.getLong(2),res.getString(3),res.getString(4),res.getInt(5),res.getInt(6)));
        }
        tabela.setItems(clientes);
        tabela.getColumns().addAll(tcId,tcCpf,tcNome,tcEmail,tcMon,tcCom);        
    }
    
    @FXML public void colocarMaisMonetizam(ActionEvent event) throws SQLException{
        ResultSet res = ClienteDAO.selecionaMaisMonetiza();
        while(res.next()){
            clientes.add(new Cliente(res.getInt(1),res.getLong(2),res.getString(3),res.getString(4),res.getInt(5),res.getInt(6)));
        }
        tabela.setItems(clientes);
        tabela.getColumns().addAll(tcId,tcCpf,tcNome,tcEmail,tcMon,tcCom);
    }
    
    
    @FXML public void abrirAtualizarCliente(ActionEvent event) throws IOException{
        try{
            if(!VerificarCliente.clienteExiste(Integer.parseInt(tfId.getText()))){
                throw new Exception("Cliente não existe!");
            }
            id = Integer.parseInt(tcId.getText());
            tcId.setText("");
            FXMLUtil.abrirJanela("/Atualizar/FXMLClienteAtualizar.fxml", "Atualizar Cliente", this, stageAtual, true);
        }catch(Exception ex){
            FXMLUtil.abrirErro(ex, this, stageAtual);
        }
    }
    
    @FXML public void abrirCadastrarCliente(ActionEvent event) throws IOException{
            FXMLUtil.abrirJanela("/Cadastrar/FXMLClienteCadastrar.fxml", "Cadastrar Cliente", this, stageAtual, true);
     }
    
    @FXML public void deletar(ActionEvent event) throws SQLException, IOException{
        try{
            if(!VerificarCliente.clienteExiste(Integer.parseInt(tfId.getText()))){
                throw new Exception("Cliente não existe!");
            }
            ClienteDAO.deletar(Integer.parseInt(tfId.getText()));
            colocarTudoTabela();
        }catch(Exception ex){
            FXMLUtil.abrirErro(ex, this, stageAtual);
        }
    }
    
    public static int getId(){
        return id;
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabela();
        try {
            colocarTudoTabela();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLClienteSelecionarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
