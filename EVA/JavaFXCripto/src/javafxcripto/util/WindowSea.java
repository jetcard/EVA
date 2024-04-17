/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcripto.util;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Jyoverar
 */
public class WindowSea {
    
    public static AnchorPane apMain;
    public static Stage StagePrincial;

//    public static String wLoginCtm = "fxml/Login.fxml"; OBSOLETO
    public static int screenStep = 0; //Pantallas
    public static int screenStepDigi = 1; //Pantallas digitacion
        
    public static Screen[] windows = new Screen[]{
        new Screen("fxml/Inicio.fxml", " "), //0    --
        new Screen("fxml/Encriptar.fxml", " "),//9 --4 si termina
        new Screen("fxml/DesEncriptar.fxml", " "),//10
        new Screen("fxml/Salida.fxml", " "),//11
    };//17
    
}
