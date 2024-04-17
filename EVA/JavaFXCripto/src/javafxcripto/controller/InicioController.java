/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcripto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafxcripto.util.WindowSea;

/**
 *
 * @author Jyoverar
 */
public class InicioController extends AppController implements Initializable {

    @FXML
    private Button btnStart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void start() throws IOException {
        WindowSea.screenStep++;
        this.openWindows();
    }

}
