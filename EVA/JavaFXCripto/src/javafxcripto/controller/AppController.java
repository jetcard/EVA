/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcripto.controller;

import java.io.IOException;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafxcripto.JavaFXCripto;
import javafxcripto.util.ControlMethod;
import javafxcripto.util.Screen;
import javafxcripto.util.WindowSea;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;


/**
 *
 * @author Jyoverar
 */
public class AppController {

    protected int timeAnimation = 200;

    @FXML
    protected void openWindows() throws IOException {
        Screen screen = WindowSea.windows[WindowSea.screenStep];
        FXMLLoader loader = new FXMLLoader(JavaFXCripto.class.getResource(screen.getXmlWindow()));
        AnchorPane newApWindow = (AnchorPane) loader.load();

        newApWindow.setPrefWidth(WindowSea.apMain.getWidth());
        newApWindow.setPrefHeight(WindowSea.apMain.getHeight());

        // assign events for controls from styleClass
        searchStyleClass(newApWindow);

        Label lblTitle = (Label) newApWindow.lookup("#title");
        Label lblSubTitle = (Label) newApWindow.lookup("#sub_title");
        Button btnMesa = (Button) newApWindow.lookup("#btn_mesa");
        Button btnVersion = (Button) newApWindow.lookup("#btn_version");
        AnchorPane paneSteps = (AnchorPane) newApWindow.lookup("#pane_steps");
        TextField txtFocus = (TextField) newApWindow.lookup(".request_focus");

        if (lblTitle != null) {
            lblTitle.setText("UTP");//WindowSea.proceso);
        }

        if (lblSubTitle != null) {
            lblSubTitle.setText(screen.getTitle());
        }

//        if (btnMesa != null) {
//            btnMesa.setVisible(screen.isDatosMesa());
//        }
//
//        if (btnVersion != null) {
//            btnVersion.setVisible(screen.isvVersion());
//        }
//
//        if (paneSteps != null) {
//            if (screen.isPaneSteps()) {
//                setStep(newApWindow);
//                paneSteps.setVisible(true);
//            } else {
//                paneSteps.setVisible(false);
//            }
//        }

        WindowSea.apMain.getChildren().setAll(newApWindow);
        System.out.println("Step: " + WindowSea.screenStep);
        System.out.println("Step Digi: " + WindowSea.screenStepDigi);
    }

    private void searchStyleClass(AnchorPane anchorPane) {
        Set<Node> nodes = anchorPane.lookupAll("TextField");
        for (Node node : nodes) {
            TextField texto = (TextField) node;
            if (texto.getStyleClass().contains("style_class_number")) {
                texto.textProperty().addListener(ControlMethod.onlyNumeric("[0-9]", texto));
            } else if (texto.getStyleClass().contains("style_class_number_two")) {
                texto.textProperty().addListener(ControlMethod.onlyNumeric("[0-9]", texto, 2));
            } else if (texto.getStyleClass().contains("style_class_number_three")) {
                texto.textProperty().addListener(ControlMethod.onlyNumeric("[0-9]", texto, 3));
            } else if (texto.getStyleClass().contains("style_class_number_eight")) {
                texto.textProperty().addListener(ControlMethod.onlyNumeric("[0-9]", texto, 8));
            }
        }
    }

}
