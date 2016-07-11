/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produto.Selecionar;

import DAO.ProdutoDAO;
import Util.FXMLUtil;
import Verificacao.VerificarProduto;
import classes.Produto;
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
public class FXMLProdutoSelecionarController implements Initializable {
    @FXML private final TableView<Produto> tabela = new TableView<>();
    @FXML private final TableColumn<Produto,String> tcId = new TableColumn<>("ID");
    @FXML private final TableColumn<Produto,String> tcNome = new TableColumn<>("Nome");
    @FXML private final TableColumn<Produto,String> tcPreco = new TableColumn<>("Preço");
    @FXML private final TableColumn<Produto,String> tcVendas = new TableColumn<>("Vendas");
    @FXML private TextField tfId;
    @FXML private AnchorPane anchorPane;
    private final Stage stageAtual = (Stage)anchorPane.getScene().getWindow();
    private final ObservableList<Produto> produtos = FXCollections.observableArrayList();
    private static int id;
    
    public void inicializarTabela(){
        tcId.setCellFactory(new PropertyValueFactory("id"));
        tcNome.setCellFactory(new PropertyValueFactory("nome"));
        tcPreco.setCellFactory(new PropertyValueFactory("preco"));
        tcVendas.setCellFactory(new PropertyValueFactory("vendas"));
    }
    
    @FXML public void colocarTudoTabela() throws SQLException{
        ResultSet res = ProdutoDAO.selecionaTudo();
        while(res.next()){
            produtos.add(new Produto(res.getInt(1),res.getString(2),res.getDouble(3),res.getInt(4)));
        }
        tabela.setItems(produtos);
        tabela.getColumns().addAll(tcId,tcNome,tcPreco,tcVendas);
    }
    
    @FXML public void colocarTudoTabela(ActionEvent event) throws SQLException{
        ResultSet res = ProdutoDAO.selecionaTudo();
        while(res.next()){
            produtos.add(new Produto(res.getInt(1),res.getString(2),res.getDouble(3),res.getInt(4)));        }
        tabela.setItems(produtos);
        tabela.getColumns().addAll(tcId,tcNome,tcPreco,tcVendas);
    }
    
    @FXML public void colocarMaisVendidos(ActionEvent event) throws SQLException{
        ResultSet res = ProdutoDAO.selecionarMaisVendidos();
        while(res.next()){
            produtos.add(new Produto(res.getInt(1),res.getString(2),res.getDouble(3),res.getInt(4)));
        }
        tabela.setItems(produtos);
        tabela.getColumns().addAll(tcId,tcNome,tcPreco,tcVendas);
    }
        
    @FXML public void abrirAtualizarProduto(ActionEvent event) throws IOException{
        try{
            if(!VerificarProduto.produtoExiste(Integer.parseInt(tfId.getText()))){
                throw new Exception("Produto não existe!");
            }
            id = Integer.parseInt(tcId.getText());
            tcId.setText("");
            FXMLUtil.abrirJanela("/Atualizar/FXMLProdutoAtualizar.fxml", "Atualizar Produto", this, stageAtual, true);
        }catch(Exception ex){
            FXMLUtil.abrirErro(ex, this, stageAtual);
        }
    }
    
    @FXML public void abrirCadastrarProduto(ActionEvent event) throws IOException{
            FXMLUtil.abrirJanela("/Cadastrar/FXMLProdutoCadastrar.fxml", "Cadastrar Produto", this, stageAtual, true);
     }
    
    @FXML public void deletar(ActionEvent event) throws SQLException, IOException{
        try{
            if(!VerificarProduto.produtoExiste(Integer.parseInt(tfId.getText()))){
                throw new Exception("Produto não existe!");
            }
            ProdutoDAO.deletar(Integer.parseInt(tfId.getText()));
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
            Logger.getLogger(FXMLProdutoSelecionarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
}
