/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Conta.Atualizar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Guillermo1en
 */
public class FXMLContaAtualizarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id = FXMLContaSelecionarController.getId();
        try {
            inicializarValores();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLContaAtualizarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
