/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcripto;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafxcripto.util.Screen;
import javafxcripto.util.WindowSea;

/**
 *
 * @author Jyoverar
 */
public class JavaFXCripto extends Application {
    
    private Stage mainStage;
    
    @Override
    public void start(Stage stagePrincipal) throws Exception {
        this.mainStage = stagePrincipal;
        loadMainWindow();
    }

    public void loadMainWindow() throws IOException {
        Screen screen = WindowSea.windows[WindowSea.screenStep];
        FXMLLoader loader = new FXMLLoader(getClass().getResource(screen.getXmlWindow()));

        WindowSea.apMain = (AnchorPane) loader.load();
        Scene scene = new Scene(WindowSea.apMain);

        this.mainStage.setScene(scene);
        WindowSea.StagePrincial = this.mainStage;
        
       //
        //this.mainStage.setFullScreen(true);
        // text: esca
        this.mainStage.setFullScreenExitHint("");
        // keycombination esc cancel
        this.mainStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
       
        this.mainStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
