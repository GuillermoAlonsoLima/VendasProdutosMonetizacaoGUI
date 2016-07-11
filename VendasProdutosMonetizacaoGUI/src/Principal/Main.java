/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Guillermo1
 */
public class Main extends Application{
    public static Stage initialStage;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Menu/MenuPrincipal/FXMLMenuPrincipal.fxml"));        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.setTitle("Menu Principal");
        initialStage = stage;
        stage.show();    
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Stage getStage(){
        return initialStage;
    }
    
}
