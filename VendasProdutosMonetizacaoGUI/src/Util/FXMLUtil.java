/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import GUI.Mensagem.FXMLMensagemController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Guillermo1
 */
public class FXMLUtil {
    public static void abrirJanela(String url,String titulo,Initializable controller,Stage primaryStage,boolean travado) throws IOException{
        Parent root = FXMLLoader.load(controller.getClass().getResource(url));        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(titulo);
        if(travado == true){
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);
        }
        stage.show();
    }
    
    public static Object getController(Initializable controller,String url){
        FXMLLoader loader = new FXMLLoader(controller.getClass().getResource(url));
        return loader.getController();        
    }
    
    public static void abrirErro(Exception ex,Initializable controlador,Stage stageAtual) throws IOException{
        FXMLMensagemController controller = (FXMLMensagemController) FXMLUtil.getController(controlador,"/Mensagem/FXMLMensagemController.fxml");
        controller.setMessage(ex.getMessage());
        FXMLUtil.abrirJanela("/Mensagem/FXMLMensagemController.fxml", "Erro", controlador, stageAtual, true);
    }
}
