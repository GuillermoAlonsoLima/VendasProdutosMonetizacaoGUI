/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Conta.Selecionar;

import DAO.ContaDAO;
import Util.FXMLUtil;
import Verificacao.VerificarConta;
import classes.Conta;
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
public class FXMLContaSelecionarController implements Initializable {
    @FXML private final TableView<Conta> tabela = new TableView<>();
    @FXML private final TableColumn<Conta,String> tcId = new TableColumn<>("ID");
    @FXML private final TableColumn<Conta,String> tcUsuario = new TableColumn<>("Usuario");
    @FXML private final TableColumn<Conta,String> tcSaldo = new TableColumn<>("Saldo");
    @FXML private final TableColumn<Conta,String> tcCliente = new TableColumn<>("Cliente");    
    @FXML private final TableColumn<Conta,String> tcCom = new TableColumn<>("Com");
    @FXML private final TableColumn<Conta,String> tcMon = new TableColumn<>("Mon");
    @FXML private TextField tfId;
    @FXML private AnchorPane anchorPane;
    private final Stage stageAtual = (Stage)anchorPane.getScene().getWindow();
    private final ObservableList<Conta> contas = FXCollections.observableArrayList();
    private static int id;
    
    public void inicializarTabela(){
        tcId.setCellFactory(new PropertyValueFactory("id"));
        tcUsuario.setCellFactory(new PropertyValueFactory("cpf"));
        tcSaldo.setCellFactory(new PropertyValueFactory("nome"));
        tcCliente.setCellFactory(new PropertyValueFactory("email"));
        tcMon.setCellFactory(new PropertyValueFactory("mon"));
        tcCom.setCellFactory(new PropertyValueFactory("com"));
    }
    
    @FXML public void colocarTudoTabela() throws SQLException{
        ResultSet res = ContaDAO.selecionaTudo();
        while(res.next()){
            contas.add(new Conta(res.getInt(1),res.getString(2),res.getString(3),res.getDouble(4),res.getInt(5),res.getInt(6),res.getInt(7)));
        }
        tabela.setItems(contas);
        tabela.getColumns().addAll(tcId,tcUsuario,tcSaldo,tcCliente,tcMon,tcCom);
    }
    
    @FXML public void colocarTudoTabela(ActionEvent event) throws SQLException{
        ResultSet res = ContaDAO.selecionaTudo();
        while(res.next()){
            contas.add(new Conta(res.getInt(1),res.getString(2),res.getString(3),res.getDouble(4),res.getInt(5),res.getInt(6),res.getInt(7)));        
        }
        tabela.setItems(contas);
        tabela.getColumns().addAll(tcId,tcUsuario,tcSaldo,tcCliente,tcMon,tcCom);
    }
    
    @FXML public void colocarMaisCompram(ActionEvent event) throws SQLException{
        ResultSet res = ContaDAO.selecionaMaisCompram();
        while(res.next()){
            contas.add(new Conta(res.getInt(1),res.getString(2),res.getString(3),res.getDouble(4),res.getInt(5),res.getInt(6),res.getInt(7)));        
        }
        tabela.setItems(contas);
        tabela.getColumns().addAll(tcId,tcUsuario,tcSaldo,tcCliente,tcMon,tcCom);
    }
    
    @FXML public void colocarMaisMonetizam(ActionEvent event) throws SQLException{
        ResultSet res = ContaDAO.selecionaMaisMonetiza();
        while(res.next()){
            contas.add(new Conta(res.getInt(1),res.getString(2),res.getString(3),res.getDouble(4),res.getInt(5),res.getInt(6),res.getInt(7)));        
        }
        tabela.setItems(contas);
        tabela.getColumns().addAll(tcId,tcUsuario,tcSaldo,tcCliente,tcMon,tcCom);
    }
    
    
    @FXML public void abrirAtualizarConta(ActionEvent event) throws IOException{
        try{
            if(!VerificarConta.contaExiste(Integer.parseInt(tfId.getText()))){
                throw new Exception("Conta não existe!");
            }
            id = Integer.parseInt(tcId.getText());
            tcId.setText("");
            FXMLUtil.abrirJanela("/Atualizar/FXMLContaAtualizar.fxml", "Atualizar Conta", this, stageAtual, true);
        }catch(Exception ex){
            FXMLUtil.abrirErro(ex, this, stageAtual);
        }
    }
    
    @FXML public void abrirCadastrarConta(ActionEvent event) throws IOException{
            FXMLUtil.abrirJanela("/Cadastrar/FXMLContaCadastrar.fxml", "Cadastrar Conta", this, stageAtual, true);
     }
    
    @FXML public void deletar(ActionEvent event) throws SQLException, IOException{
        try{
            if(!VerificarConta.contaExiste(Integer.parseInt(tfId.getText()))){
                throw new Exception("Conta não existe!");
            }
            ContaDAO.deletar(Integer.parseInt(tfId.getText()));
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
            Logger.getLogger(FXMLContaSelecionarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
}
